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

import cn.enaium.mineconf.core.common.text.Text
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.HoverEvent
import net.minecraft.network.chat.Style
import java.net.URI
import kotlin.io.path.Path

/**
 * @author Enaium
 */
fun Text.toMinecraft(): Component {
    return Component.literal(text).apply {
        this@toMinecraft.siblings.forEach {
            this.append(it.toMinecraft())
        }
        this@toMinecraft.style?.also {
            this.style = it.toMinecraft()
        }
    }
}

fun cn.enaium.mineconf.core.common.text.Style.toMinecraft(): Style {
    var style = Style.EMPTY
    this@toMinecraft.color?.also {
        style = style.withColor(ChatFormatting.valueOf(it.name))
    }
    style = style.withBold(this@toMinecraft.bold)
    style = style.withItalic(this@toMinecraft.italic)
    style = style.withUnderlined(this@toMinecraft.underline)
    style = style.withStrikethrough(this@toMinecraft.strikethrough)
    style = style.withObfuscated(this@toMinecraft.obfuscated)
    style = style.withInsertion(this@toMinecraft.insertion)
    this@toMinecraft.hover?.also {
        style = style.withHoverEvent(
            when (it.action) {
                cn.enaium.mineconf.core.common.text.HoverEvent.Action.SHOW_TEXT -> HoverEvent.ShowText(
                    it.value.toMinecraft()
                )
            }
        )
    }
    this@toMinecraft.click?.also {
        style = style.withClickEvent(
            when (it.action) {
                cn.enaium.mineconf.core.common.text.ClickEvent.Action.OPEN_URL -> ClickEvent.OpenUrl(URI.create(it.value))
                cn.enaium.mineconf.core.common.text.ClickEvent.Action.OPEN_FILE -> ClickEvent.OpenFile(Path(it.value))
                cn.enaium.mineconf.core.common.text.ClickEvent.Action.RUN_COMMAND -> ClickEvent.RunCommand(it.value)
                cn.enaium.mineconf.core.common.text.ClickEvent.Action.SUGGEST_COMMAND -> ClickEvent.SuggestCommand(
                    it.value
                )
            }
        )
    }
    return style
}