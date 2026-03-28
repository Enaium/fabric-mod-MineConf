/*
 * Copyright 2026 Enaium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.enaium.mineconf.core.conf

import cn.enaium.mineconf.core.type.Vec2

/**
 * @author Enaium
 */
class Vec2Conf<T : Number>(
    id: String,
    name: String,
    description: String,
    value: Vec2<T>,
    widget: Widget?,
    val rangeX: Range<T>?,
    val rangeY: Range<T>?,
    val stepX: T?,
    val stepY: T?,
) : Conf<Vec2<T>>(id, name, description, value, widget)
