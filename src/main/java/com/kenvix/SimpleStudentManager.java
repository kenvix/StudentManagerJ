//--------------------------------------------------
// Class SimpleCommandLineUI
//--------------------------------------------------
// Written by Kenvix <i@kenvix.com>
//--------------------------------------------------

package com.kenvix;

import com.kenvix.studentmanager.model.mysql.PersonModel;
import com.kenvix.utils.preferences.ServerEnv;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;

import java.util.Map;

public class SimpleStudentManager {
    public void setup() {
        Javalin app = Javalin.create().start(ServerEnv.INSTANCE.getHttpPort());
        JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");
        app.get("/", ctx -> {
            var persons = PersonModel.INSTANCE.findAll();
            ctx.render("/persons_list.ftl", Map.of("persons", persons));
        });

        app.get("/person/add", ctx -> {
            var persons = PersonModel.INSTANCE.findAll();
            ctx.render("/persons_list.ftl", Map.of("persons", persons));
        });
    }
}
