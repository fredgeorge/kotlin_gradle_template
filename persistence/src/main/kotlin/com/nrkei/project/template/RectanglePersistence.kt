/*
 * Copyright (c) 2025-26 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

package com.nrkei.project.template

import com.nrkei.project.issue.fromBase64
import com.nrkei.project.issue.toBase64
import com.nrkei.project.template.Rectangle.RectangleDto

// Understands rendering of a Rectangle in a transmittable format

internal fun Rectangle.toMemento() = toBase64(toDto())

internal fun Rectangle.Companion.fromMemento(memento: String) =
    fromBase64<RectangleDto>(memento).let { rectangleDto ->
        Rectangle(rectangleDto.length, rectangleDto.width)
    }