package class_22_10_24;

import class_22_10_24.alternative.HelperAlternative1Impl;
import class_22_10_24.base.Helper;
import class_22_10_24.base.HelperDefaultImpl;
import class_22_10_24.base.Simulation;
import class_22_10_24.base.SimulationDefaultImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Guice;

class RunSimulation {

    public static void main( String[] args ){

        AbstractModule simulator = new AbstractModule() {
            @Override
            protected void configure () {
                bind(Simulation.class).to(SimulationDefaultImpl.class);
                // bind(Helper.class).to(HelperAlternative1Impl.class);
            }
        };

        AbstractModule helper = new AbstractModule() {
            @Override
            protected void configure() {
                bind(Helper.class).to(HelperAlternative1Impl.class);
            }
        };


        Injector injector = Guice.createInjector(simulator);

        Simulation simulation = injector.getInstance(Simulation.class);
        simulation.doStep();

    }
}
