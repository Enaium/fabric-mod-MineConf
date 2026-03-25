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

package cn.enaium.mineconf.config

import cn.enaium.mineconf.ConfBuilder
import cn.enaium.mineconf.annotation.ConfField

/**
 * @author Enaium
 */
object MineConfConfig {
    @ConfField
    val fontScale =
        ConfBuilder.create()
            .id("font_scale")
            .name("Font Scale")
            .description("Font scale of the imgui")
            .literal<Float>().build(2f)

    @ConfField
    val lang =
        ConfBuilder.create()
            .id("lang")
            .name("Lang")
            .description("Language for MineConf")
            .enumeration<Lang>()
            .type(Lang::class.java)
            .build(Lang.EN_US)

    enum class Lang(val title: String) {
        EN_US("English"),
        ZH_CN("中文");

        override fun toString(): String {
            return title
        }
    }
}