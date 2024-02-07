/*
 * Copyright 2023, Seqera Labs
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
 *
 */

package io.seqera.wave.core.spec

import spock.lang.Specification

/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
class ObjectSpecTest extends Specification {

    def 'should create an object from json string' () {
        given:
        def SPEC = '''
                {
                  "mediaType":"application/vnd.oci.image.config.v1+json",
                  "digest":"sha256:3f57d9401f8d42f986df300f0c69192fc41da28ccc8d797829467780db3dd741",
                  "size":581
               }
            '''

        when:
        def result = ObjectRef.of(SPEC)
        then:
        result == new ObjectRef(
                "application/vnd.oci.image.config.v1+json",
                "sha256:3f57d9401f8d42f986df300f0c69192fc41da28ccc8d797829467780db3dd741",
                581)
    }

    def 'should create an object' () {
        when:
        def result = ObjectRef.of([mediaType: 'foo', digest: 'sha256:12345', size: 100])
        then:
        result == new ObjectRef('foo', 'sha256:12345', 100)
    }

    def 'should create an object given a list of maps' () {
        given:
        def list = [
                [mediaType:'foo', digest: 'sha256:12345', size:111L],
                [mediaType:'bar', digest: 'sha256:67890', size:222L] ]
        when:
        def result = ObjectRef.of(list)
        then:
        result[0] == new ObjectRef('foo', 'sha256:12345', 111)
        result[1] == new ObjectRef('bar', 'sha256:67890', 222)
    }
}
