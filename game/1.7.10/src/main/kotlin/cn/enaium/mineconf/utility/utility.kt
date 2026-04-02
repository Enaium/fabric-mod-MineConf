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

import cn.enaium.mineconf.core.common.text.Style
import cn.enaium.mineconf.core.common.text.Text
import net.minecraft.text.*
import net.minecraft.util.Formatting

/**
 * @author Enaium
 */
fun Text.toMinecraft(): net.minecraft.text.Text {
    return LiteralText(this@toMinecraft.text).apply {
        this@toMinecraft.siblings.forEach {
            this.append(it.toMinecraft())
        }
        this@toMinecraft.style?.also {
            this.style = it.toMinecraft()
        }
    }
}

fun Style.toMinecraft(): net.minecraft.text.Style {
    return net.minecraft.text.Style().apply {
        this@toMinecraft.color?.also {
            this.setFormatting(Formatting.valueOf(it.name))
        }
        this.isBold = this@toMinecraft.bold
        this.isItalic = this@toMinecraft.italic
        this.setUnderline(this@toMinecraft.underline)
        this.isStrikethrough = this@toMinecraft.strikethrough
        this.isObfuscated = this@toMinecraft.obfuscated
        this@toMinecraft.hover?.also {
            this.hoverEvent = HoverEvent(HoverEventAction.valueOf(it.action.name), it.value.toMinecraft())
        }
        this@toMinecraft.click?.also {
            this.clickEvent = ClickEvent(ClickEventAction.valueOf(it.action.name), it.value)
        }
    }
}