//--------------------------------------------------
// Class SimpleCommandLineUI
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix;

import com.kenvix.studentmanager.model.mysql.BaseModel;
import com.kenvix.studentmanager.model.mysql.PersonModel;
import com.kenvix.studentmanager.model.mysql.StudentClassMapModel;
import com.kenvix.studentmanager.orm.enums.PersonsSex;
import com.kenvix.studentmanager.orm.enums.PersonsStatus;
import com.kenvix.studentmanager.orm.enums.PersonsType;
import com.kenvix.studentmanager.orm.tables.pojos.StudentClassMap;
import com.kenvix.studentmanager.orm.tables.pojos.Persons;
import com.kenvix.utils.preferences.ServerEnv;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import kotlin.Unit;
import io.javalin.http.Context;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleStudentManager {
    public Context renderOk(Context ctx) {
        return ctx.render("/ok.ftl");
    }

    public void renderError(Context ctx, Throwable exception) {
        ctx.render("/error.ftl", Map.of(
                "message", exception.getMessage(),
                "name", exception.getClass().getName(),
                "trace", Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n"))
        ));
    }

    public void setup() {
        Javalin app = Javalin.create().start(ServerEnv.INSTANCE.getHttpPort());
        JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");

        app.exception(Exception.class, (e, ctx) -> {
            // handle general exceptions here
            // will not trigger if more specific exception-mapper found
            renderError(ctx, e);
        });

        app.get("/", ctx -> {
            var persons = PersonModel.INSTANCE.findAll();
            ctx.render("/persons_list.ftl", Map.of("persons", persons));
        });

        app.get("/person/delete/:id", ctx -> {
            PersonModel.INSTANCE.deleteById(Long.valueOf(ctx.pathParam("id")));
            renderOk(ctx);
        });

        app.post("/person/add", ctx -> {
            var person = new Persons();
            var params = ctx.formParamMap();

            PersonModel.INSTANCE.transactionThreadLocal(trans -> {
                if (params.containsKey("id") && !params.get("id").get(0).isBlank())
                    person.setId(Long.valueOf(params.get("id").get(0)));

                person.setName(params.get("name").get(0));
                person.setSex(PersonsSex.valueOf(params.get("sex").get(0)));
                person.setType(PersonsType.valueOf(params.get("type").get(0)));
                person.setStatus(PersonsStatus.valueOf(params.get("status").get(0)));
                person.setIsGraduable(params.containsKey("is_graduable") ? (byte) 1 : (byte) 0);
                PersonModel.INSTANCE.insert(person);

                if (params.containsKey("class") && !params.get("class").get(0).isBlank()) {
                    var classIdMap = new StudentClassMap();
                    classIdMap.setStudentId(person.getId());
                    classIdMap.setClassId(Long.valueOf(params.get("class").get(0)));
                    StudentClassMapModel.INSTANCE.insert(classIdMap);
                }

                renderOk(ctx);
                return ctx;
            });
        });
    }
}
