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
    private static Map<String, Map<String, String>> nameMap = Map.of(
            "type", Map.of(
                    "teacher", "教师",
                    "student_undergraduate", "本科生",
                    "student_postgraduate", "研究生"
            ),
            "status", Map.of(
                    "normal", "正常",
                    "dropped", "已退学或辞职",
                    "graduated", "已毕业"
            ),
            "sex", Map.of(
                    "unknown", "未知",
                    "female", "女",
                    "male", "男"
            ),
            "intbool", Map.of(
                    "0", "否",
                    "1", "是"
            )
    );

    private static Context render(Context ctx, String path, Map<String, Object> data) {
        return ctx.render("/template/" + path + ".ftl", data);
    }

    private static Context render(Context ctx, String path) {
        return render(ctx, path, Map.of());
    }

    private static Context renderOk(Context ctx) {
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
            var papers = switch (Objects.requireNonNull(ctx.queryParam("filter", ""))) {
                case "person_id" -> PaperModel.INSTANCE.fetchWithPersonByPersonId(Long.valueOf(Objects.requireNonNull(ctx.queryParam("person_id", ""))));
                default -> PaperModel.INSTANCE.fetchAllWithPerson();
            };

            render(ctx, "paper_list", Map.of("papers", papers));
        });

        app.get("/paper/delete/:id", ctx -> {
            PaperModel.INSTANCE.deleteById(Long.valueOf(ctx.pathParam("id")));
            renderOk(ctx);
        });

        app.post("/paper/add", ctx -> {
            var paper = new Papers();
            updatePaper(ctx, paper);
            PaperModel.INSTANCE.insert(paper);
            renderOk(ctx);
        });

        app.get("/paper/edit/:id", ctx -> {
            var paper = Tools.assertNotEmpty(PaperModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            render(ctx, "paper_edit", Map.of("paper", paper));
        });

        app.post("/paper/edit/:id", ctx -> {
            var paper = Tools.assertNotEmpty(PaperModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            updatePaper(ctx, paper);
            PaperModel.INSTANCE.update(paper);
            renderOk(ctx);
        });

        app.get("/class", ctx -> {
            var classes = switch (Objects.requireNonNull(ctx.queryParam("filter", ""))) {
                case "id" -> ClassModel.INSTANCE.fetchWithMasterByClassId(Long.valueOf(Objects.requireNonNull(ctx.queryParam("id", ""))));
                default -> ClassModel.INSTANCE.fetchAllWithMaster();
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
            ClassModel.INSTANCE.insert(clazz);
            renderOk(ctx);
        });

        app.get("/class/edit/:id", ctx -> {
            var clazz = Tools.assertNotEmpty(ClassModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            render(ctx, "class_edit", Map.of("clazz", clazz));
        });

        app.post("/class/edit/:id", ctx -> {
            var clazz = Tools.assertNotEmpty(ClassModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            updateClass(ctx, clazz);
            ClassModel.INSTANCE.update(clazz);
            renderOk(ctx);
        });

        app.get("/person", ctx -> {
            var persons = switch (Objects.requireNonNull(ctx.queryParam("filter", ""))) {
                case "type" -> PersonModel.INSTANCE.findByType(PersonsType.valueOf(ctx.queryParam("type", "")));
                case "id" -> PersonModel.INSTANCE.findById(Long.valueOf(Tools.assertNotEmpty(ctx.queryParam("id", ""))));
                case "class_id" -> PersonModel.INSTANCE.fetchByClazz(Long.valueOf(Tools.assertNotEmpty(ctx.queryParam("class_id", ""))));
                default -> PersonModel.INSTANCE.findAll();
            };
            render(ctx, "person_list", Map.of("persons", persons, "names", nameMap));
        });

        app.get("/person/delete/:id", ctx -> {
            PersonModel.INSTANCE.deleteById(Long.valueOf(ctx.pathParam("id")));
            renderOk(ctx);
        });

        app.post("/person/add", ctx -> {
            var person = new Persons();
            updatePerson(ctx, person);
            PersonModel.INSTANCE.insert(person);
            renderOk(ctx);
        });

        app.get("/person/edit/:id", ctx -> {
            var person = Tools.assertNotEmpty(PersonModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            render(ctx, "person_edit", Map.of("person", person, "names", nameMap));
        });

        app.post("/person/edit/:id", ctx -> {
            var person = Tools.assertNotEmpty(PersonModel.INSTANCE.fetchById(Long.valueOf(ctx.pathParam("id")))).get(0);
            updatePerson(ctx, person);
            PersonModel.INSTANCE.update(person);
            renderOk(ctx);
        });
    }

    private void updateClass(Context ctx, Classes clazz) {
        var params = ctx.formParamMap();

        clazz.setId(Long.valueOf(Tools.assertNotEmpty(params.get("class_id")).get(0)));
        clazz.setMasterId(Long.valueOf(Tools.assertNotEmpty(params.get("master_id")).get(0)));
    }

    private void updatePaper(Context ctx, Papers paper) {
        var params = ctx.formParamMap();

        if (params.containsKey("id") && !params.get("id").get(0).isBlank())
            paper.setId(Long.valueOf(Tools.assertNotEmpty(params.get("id")).get(0)));

        paper.setPersonId(Long.valueOf(Tools.assertNotEmpty(params.get("person_id")).get(0)));
        paper.setTitle(Tools.assertNotEmpty(params.get("title")).get(0));
    }

    private void updatePerson(Context ctx, Persons person) {
        var params = ctx.formParamMap();

        if (params.containsKey("id") && !params.get("id").get(0).isBlank())
            person.setId(Long.valueOf(Tools.assertNotEmpty(params.get("id")).get(0)));

        person.setName(Tools.assertNotEmpty(params.get("name")).get(0));
        person.setSex(PersonsSex.valueOf(Tools.assertNotEmpty(params.get("sex")).get(0)));
        person.setType(PersonsType.valueOf(Tools.assertNotEmpty(params.get("type")).get(0)));
        person.setStatus(PersonsStatus.valueOf(Tools.assertNotEmpty(params.get("status")).get(0)));
        person.setIsGraduable(params.containsKey("is_graduable") ? (byte) 1 : (byte) 0);
        person.setClazz(Long.valueOf(Tools.assertNotEmpty(params.get("class_id")).get(0)));
    }
}
