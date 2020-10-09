//--------------------------------------------------
// Class SimpleCommandLineUI
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix;

import com.kenvix.studentmanager.model.mysql.*;
import com.kenvix.studentmanager.orm.enums.PersonsSex;
import com.kenvix.studentmanager.orm.enums.PersonsStatus;
import com.kenvix.studentmanager.orm.enums.PersonsType;
import com.kenvix.studentmanager.orm.tables.pojos.Classes;
import com.kenvix.studentmanager.orm.tables.pojos.Papers;
import com.kenvix.studentmanager.orm.tables.pojos.Persons;
import com.kenvix.utils.preferences.ServerEnv;
import com.kenvix.web.utils.Tools;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

final public class SimpleStudentManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Context render(Context ctx, String path, Map<String, Object> data) {
        return ctx.render("/template/" + path + ".ftl", data);
    }

    private Context render(Context ctx, String path) {
        return render(ctx, path, Map.of());
    }

    private Context renderOk(Context ctx) {
        return render(ctx, "ok");
    }

    private void renderError(Context ctx, Throwable exception) {
        logger.info("Request failed: " + ctx.method() + ": "  + ctx.url(), exception);
        render(ctx, "error", Map.of(
                "simpleName", exception.getClass().getSimpleName(),
                "message", exception.getMessage(),
                "name", exception.getClass().getName(),
                "trace", Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n"))
        ));
    }

    public void setup() {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
        }).start(ServerEnv.INSTANCE.getHttpPort());
        JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");

        app.exception(Exception.class, (e, ctx) -> {
            // handle general exceptions here
            // will not trigger if more specific exception-mapper found
            renderError(ctx, e);
        });

        app.get("/", ctx -> {
            render(ctx, "index");
        });

        app.get("/paper", ctx -> {
            var papers = switch (Tools.assertNotEmpty(ctx.queryParam("filter", ""))) {
                case "person_id" -> PaperModel.INSTANCE.fetchByPersonId(Long.valueOf(Objects.requireNonNull(ctx.queryParam("person_id", ""))));
                default -> PaperModel.INSTANCE.findAll();
            };

            render(ctx, "paper_list", Map.of("papers", papers));
        });

        app.get("/paper/delete/:id", ctx -> {
            PaperModel.INSTANCE.deleteById(Long.valueOf(ctx.pathParam("id")));
            renderOk(ctx);
        });

        app.post("/paper/add", ctx -> {
            var paper = new Papers();

        });

        app.get("/paper/edit/:id", ctx -> {
            var paper = Tools.assertNotEmpty(PaperModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            render(ctx, "paper_edit", Map.of("paper", paper));
        });

        app.post("/class/edit/:id", ctx -> {
            var paper = Tools.assertNotEmpty(PaperModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);

        });

        app.get("/class", ctx -> {
            var classes = switch (Tools.assertNotEmpty(ctx.queryParam("filter", ""))) {
                case "person_id" -> ClassModel.INSTANCE.fetchByPersonId(Long.valueOf(Objects.requireNonNull(ctx.queryParam("person_id", ""))));
                default -> ClassModel.INSTANCE.findAll();
            };

            render(ctx, "class_list", Map.of("classes", classes));
        });

        app.get("/class/delete/:id", ctx -> {
            ClassModel.INSTANCE.deleteById(Long.valueOf(ctx.pathParam("id")));
            renderOk(ctx);
        });

        app.post("/class/add", ctx -> {
            var clazz = new Classes();
            updateClass(ctx, clazz);
        });

        app.get("/class/edit/:id", ctx -> {
            var clazz = Tools.assertNotEmpty(ClassModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            render(ctx, "class_edit", Map.of("clazz", clazz));
        });

        app.post("/class/edit/:id", ctx -> {
            var clazz = Tools.assertNotEmpty(ClassModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            updateClass(ctx, clazz);
        });

        app.get("/person", ctx -> {
            var persons = switch (Tools.assertNotEmpty(ctx.queryParam("filter", ""))) {
                case "type" -> PersonModel.INSTANCE.findByType(PersonsType.valueOf(ctx.queryParam("type", "")));
                default -> PersonModel.INSTANCE.findAll();
            };
            render(ctx, "persons_list", Map.of("persons", persons));
        });

        app.get("/person/delete/:id", ctx -> {
            PersonModel.INSTANCE.deleteById(Long.valueOf(ctx.pathParam("id")));
            renderOk(ctx);
        });

        app.post("/person/add", ctx -> {
            var person = new Persons();
            updatePerson(ctx, person);
        });

        app.get("/person/edit/:id", ctx -> {
            var person = Tools.assertNotEmpty(PersonModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            render(ctx, "person_edit", Map.of("person", person));
        });

        app.post("/person/edit/:id", ctx -> {
            var person = Tools.assertNotEmpty(PersonModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            updatePerson(ctx, person);
        });
    }

    private void updateClass(Context ctx, Classes clazz) {
        var params = ctx.formParamMap();

        ClassModel.INSTANCE.transactionThreadLocal(trans -> {
            if (params.containsKey("id") && !params.get("id").get(0).isBlank())
                clazz.setId(Long.valueOf(Tools.assertNotEmpty(params.get("id")).get(0)));

            clazz.setId(Long.valueOf(Tools.assertNotEmpty(params.get("class_id")).get(0)));
            clazz.setMasterId(Long.valueOf(Tools.assertNotEmpty(params.get("master_id")).get(0)));
            ClassModel.INSTANCE.insert(clazz);

            renderOk(ctx);
            return ctx;
        });
    }

    private void updatePerson(Context ctx, Persons person) {
        var params = ctx.formParamMap();

        PersonModel.INSTANCE.transactionThreadLocal(trans -> {
            if (params.containsKey("id") && !params.get("id").get(0).isBlank())
                person.setId(Long.valueOf(Tools.assertNotEmpty(params.get("id")).get(0)));

            person.setName(Tools.assertNotEmpty(params.get("name")).get(0));
            person.setSex(PersonsSex.valueOf(Tools.assertNotEmpty(params.get("sex")).get(0)));
            person.setType(PersonsType.valueOf(Tools.assertNotEmpty(params.get("type")).get(0)));
            person.setStatus(PersonsStatus.valueOf(Tools.assertNotEmpty(params.get("status")).get(0)));
            person.setIsGraduable(params.containsKey("is_graduable") ? (byte) 1 : (byte) 0);
            person.setClazz(Long.valueOf(Tools.assertNotEmpty(params.get("class_id")).get(0)));
            PersonModel.INSTANCE.insert(person);

            renderOk(ctx);
            return ctx;
        });
    }
}
