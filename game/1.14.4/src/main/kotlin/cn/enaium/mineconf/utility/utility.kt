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

package cn.enaium.mineconf.utility

import net.minecraft.text.*
import net.minecraft.util.Formatting

/**
 * @author Enaium
 */
fun cn.enaium.mineconf.core.common.text.Text.toMinecraft(): Text {
    return LiteralText(text).apply {
        this@toMinecraft.siblings.forEach {
            this.append(it.toMinecraft())
        }
        this@toMinecraft.style?.also {
            this.style = it.toMinecraft()
        }
    }
}

fun cn.enaium.mineconf.core.common.text.Style.toMinecraft(): Style {
    return Style().apply {
        this@toMinecraft.color?.also {
            this.color = Formatting.valueOf(it.name)
        }
        this.isBold = this@toMinecraft.bold
        this.isItalic = this@toMinecraft.italic
        this.setUnderline(this@toMinecraft.underline)
        this.isStrikethrough = this@toMinecraft.strikethrough
        this.isObfuscated = this@toMinecraft.obfuscated
        this.insertion = this@toMinecraft.insertion

        this@toMinecraft.hover?.also {
            this.hoverEvent = when (it.action) {
                cn.enaium.mineconf.core.common.text.HoverEvent.Action.SHOW_TEXT -> HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    it.value.toMinecraft()
                )
            }
        }
        this@toMinecraft.click?.also {
            this.clickEvent = when (it.action) {
                cn.enaium.mineconf.core.common.text.ClickEvent.Action.OPEN_URL -> ClickEvent(
                    ClickEvent.Action.OPEN_URL,
                    it.value
                )

                cn.enaium.mineconf.core.common.text.ClickEvent.Action.OPEN_FILE -> ClickEvent(
                    ClickEvent.Action.OPEN_FILE,
                    it.value
                )

                cn.enaium.mineconf.core.common.text.ClickEvent.Action.RUN_COMMAND -> ClickEvent(
                    ClickEvent.Action.RUN_COMMAND,
                    it.value
                )

                cn.enaium.mineconf.core.common.text.ClickEvent.Action.SUGGEST_COMMAND -> ClickEvent(
                    ClickEvent.Action.SUGGEST_COMMAND,
                    it.value
                )
            }
        }
    }
}