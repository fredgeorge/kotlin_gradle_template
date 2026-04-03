/*
 * Copyright (c) 2025-26 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

package com.nrkei.project.template.unit

import com.nrkei.project.template.Rectangle
import com.nrkei.project.template.fromMemento
import com.nrkei.project.template.toMemento
import org.junit.jupiter.api.Test

// Ensures Rectangles can be persisted and restored
internal class RectanglePersistenceTest {

    @Test fun `Rectangle persisted and restored`() {
        Rectangle(4.0, 6.0).also {original ->
            original.toMemento().also {memento ->
                Rectangle.fromMemento(memento).also {restored ->
                    assert(original.area() == restored.area())
                }
            }
        }
    }
}