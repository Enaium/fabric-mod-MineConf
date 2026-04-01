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

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * @author Enaium
 */
data class CollectionConf<T>(
    override val id: String,
    override val name: String,
    override val description: String,
    override var defaultValue: Collection<T>,
    override var widget: Widget?,
    @get:JsonIgnore
    val converter: (String) -> T,
    val options: Collection<T>?
) : Conf<Collection<T>>(id, name, description, defaultValue, widget)