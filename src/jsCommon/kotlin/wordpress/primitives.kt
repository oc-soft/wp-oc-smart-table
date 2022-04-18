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

@file:JsModule("@wordpress/primitives")
@file:JsNonModule

package wordpress.primitives

external val Circle: (dynamic)-> dynamic

external val G: (dynamic)-> dynamic

external val Path: (dynamic)-> dynamic

external val Polygon: (dynamic)-> dynamic

external val Rect: (dynamic)-> dynamic

external val Defs: (dynamic)-> dynamic

external val RadialGradient: (dynamic)-> dynamic

external val LinearGradient: (props: dynamic)-> dynamic

external val Stop: (dynamic)-> dynamic

external val SVG: (dynamic)-> dynamic 

// vi: se ts=4 sw=4 et:
