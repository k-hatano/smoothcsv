/*
 * Copyright 2014 kohii.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.smoothcsv.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.smoothcsv.commons.exception.UnexpectedException;

/**
 *
 * @author kohii
 */
public class SingletonInstanceManager {

  private static final Map<Class<?>, Object> INSTANCES = new HashMap<>();

  public static Object getSingleInstance(Class<?> clazz) {
    Object instance = INSTANCES.get(clazz);
    if (instance == null) {
      try {
        instance = clazz.newInstance();
      } catch (InstantiationException | IllegalAccessException ex) {
        throw new UnexpectedException();
      }
      INSTANCES.put(clazz, instance);
    }
    return instance;
  }

  public static <T> T getSingleInstance(Class<? extends T> clazz, Supplier<T> factory) {
    Object instance = INSTANCES.get(clazz);
    if (instance == null) {
      instance = factory.get();
      INSTANCES.put(clazz, instance);
    }
    return (T) instance;
  }
}
