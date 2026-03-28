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
data class MultimapConf<O, M>(
    override val id: String,
    override val name: String,
    override val description: String,
    override var defaultValue: Map<O, Collection<M>>,
    override var widget: Widget?,
    @field:JsonIgnore
    val keyConverter: (String) -> O,
    val keyOptions: Collection<O>?,
    @field:JsonIgnore
    val valueConverter: (String) -> M,
    val valueOptions: Collection<M>?
) : Conf<Map<O, Collection<M>>>(id, name, description, defaultValue, widget)
