/*
 * Copyright 2022 oc-soft
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JsModule("@wordpress/i18n")
@file:JsNonModule
package wordpress.i18n


@JsName("__")
external fun gettext(text: String, 
    domain: String = definedExternally): String

@JsName("_x")
external fun dcgettext(text: String,
    context: String, 
    domain: String = definedExternally): String


@JsName("_n")
external fun ngettext(single: String, 
    plural: String, 
    number: Number, 
    domain: String = definedExternally): String


external fun _ns(
    single: String, 
    plural: String, 
    number: Number, 
    context: String,
    domain: String = definedExternally): String


external fun isRTL(): Boolean

external fun hasTranslation(
    single: String,
    context: String,
    domain: String): Boolean


external fun sprintf(
    format: String,
    vararg args: dynamic): String

// vi: se ts=4 sw=4 et:
