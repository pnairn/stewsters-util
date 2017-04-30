package com.stewsters.test.planner;

import com.stewsters.util.planner.Action;
import com.stewsters.util.planner.Fitness;
import com.stewsters.util.planner.Planner;
import com.stewsters.util.planner.WorldState;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlannerTest {


    @Test
    public void testPlannerOnGame() {

        WorldState startingWorldState = new WorldState();
        startingWorldState.robotHasGear = true;
        int maxCost = 100;

        List<Action<WorldState>> actions = Arrays.asList(
                new Action<>(
                        "Load Gear",
                        (WorldState w) -> {
                            return !w.robotHasGear && !w.atAirship;
                        },
                        (WorldState w) -> {
                            w.robotHasGear = true;
                            return w;
                        },
                        10),

                new Action<>(
                        "Place Gear",
                        (WorldState w) -> {
                            return w.robotHasGear && w.atAirship;
                        },
                        (WorldState w) -> {
                            w.robotHasGear = false;
                            w.scoredGears++;
                            return w;
                        },
                        10),

                new Action<>(
                        "Go To Airship",
                        (WorldState w) -> {
                            return !w.atAirship;
                        },
                        (WorldState w) -> {
                            w.atAirship = true;
                            return w;
                        },
                        10),

                new Action<>(
                        "Go To Loading",
                        (WorldState w) -> {
                            return w.atAirship;
                        },
                        (WorldState w) -> {
                            w.atAirship = false;
                            return w;
                        },
                        10)
        );

        Planner p = new Planner<WorldState>();

        Optional<List<Action>> plan = p.plan(startingWorldState,
                new Fitness<WorldState>() {
                    @Override
                    public float fitness(WorldState worldState) {
                        return worldState.scoredGears;
                    }
                },
                actions,
                maxCost
        );

        if (plan.isPresent()) {
            plan.get().stream()
                    .map(Action::getName)
                    .forEach(System.out::println);
        } else {
            System.out.println("Nope");
        }


    }

}
