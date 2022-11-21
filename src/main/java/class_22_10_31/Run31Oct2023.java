package class_22_10_31;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.*;
import org.matsim.core.controler.corelisteners.ControlerDefaultCoreListenersModule;
import org.matsim.core.scenario.ScenarioByInstanceModule;
import org.matsim.core.scenario.ScenarioUtils;

public class Run31Oct2023 {

    public static void main(String[] args) {

        Config config = ConfigUtils.loadConfig("scenarios/equil/config.xml");
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        config.controler().setLastIteration(2);

        Scenario scenario = ScenarioUtils.loadScenario(config);

        AbstractModule module = new AbstractModule() {
            @Override
            public void install() {
                install(new NewControlerModule());
                install(new ControlerDefaultCoreListenersModule());
                install(new ControlerDefaultsModule());
                install(new ScenarioByInstanceModule(scenario));
            }
        };

        com.google.inject.Injector injector = Injector.createInjector(config, module);
        ControlerI controlerI = injector.getInstance(ControlerI.class);
        controlerI.run();
    }




    interface Abc {
        void doSomething();
    }


    private static class AbcImpl implements Abc {

        @Inject
        private Helper helper;

        @Override
        public void doSomething() {
            System.out.println("bla AbcImpl");
            helper.help();
        }
    }

    private static class AbcImpl2 implements Abc {

        @Override
        public void doSomething() {
            System.out.println("bla AbcImpl2");
        }
    }

    interface Helper {
        void help();
    }

    private static class HelperImpl implements Helper {
        @Override
        public void help() {
            System.out.println("bla HelperImpl");
        }
    }
}
