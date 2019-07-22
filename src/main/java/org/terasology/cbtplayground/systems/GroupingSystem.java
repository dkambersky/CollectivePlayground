/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.cbtplayground.systems;

import org.terasology.cbtplayground.components.GroupComponent;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.behavior.BehaviorComponent;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.registry.In;


@RegisterSystem(RegisterMode.AUTHORITY)
public class GroupingSystem extends BaseComponentSystem {

    @In
    private EntityManager entityManager;


    @Override
    public void initialise() {

    }


    @Command
    public String groupOn() {

        Iterable<EntityRef> entities = entityManager.getEntitiesWith(BehaviorComponent.class);

        for (EntityRef entity : entities) {
            GroupComponent comp = new GroupComponent();
            comp.group = entity.getId()+"";

            entity.addComponent(comp);

        }

        return "Success!";
    }

    @Command
    public void groupOff() {
        Iterable<EntityRef> entities = entityManager.getEntitiesWith(GroupComponent.class);
        for (EntityRef entity : entities) {
            entity.removeComponent(GroupComponent.class);
        }
    }



}
