/*
 * Copyright (C) 2012 Facebook, Inc.
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
package io.airlift.drift.codec.metadata;

import io.airlift.drift.annotations.ThriftField;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static com.google.common.base.MoreObjects.toStringHelper;
import static io.airlift.drift.codec.metadata.ReflectionHelper.resolveFieldType;

class FieldExtractor
        extends Extractor
{
    private final Type thriftStructType;
    private final Field field;

    FieldExtractor(Type thriftStructType, Field field, ThriftField annotation, FieldKind fieldKind)
    {
        super(annotation, fieldKind);
        this.thriftStructType = thriftStructType;
        this.field = field;
    }

    public Field getField()
    {
        return field;
    }

    @Override
    public String extractName()
    {
        return field.getName();
    }

    @Override
    public Type getJavaType()
    {
        return resolveFieldType(thriftStructType, field.getGenericType());
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("field", field)
                .toString();
    }
}
