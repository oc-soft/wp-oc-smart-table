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

@file:JsModule("@wordpress/element")
@file:JsNonModule

package wordpress.element

external fun createElement(
    type: dynamic,
    config: dynamic,
    vararg children: dynamic): dynamic

external fun createElement(
    builder: (dynamic)->dynamic,
    config: dynamic,
    vararg children: dynamic): dynamic


external fun cloneElement(
    element: dynamic,
    props: dynamic,
    vararg children: dynamic): dynamic


/**
 * render attributes
 */
external fun renderAttributes(
    props: dynamic): String


// vi: se ts=4 sw=4 et:
