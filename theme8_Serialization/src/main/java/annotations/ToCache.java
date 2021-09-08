package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//TODO 6.	Любые полезные настройки на ваш вкус.
//TODO 7.	Все настройки кеша должны быть optional и иметь дефолтные настройки.

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToCache {
    //TODO 1.какие методы кешировать и как: Просчитанный результат хранить в памяти JVM
    // или сериализовывать в файле на диск.
    CacheType cacheType() default CacheType.IN_MEMORY;
    //TODO 4.	Возможность указывать название файла/ключа по которому будем храниться значение. Если не задано -
    // использовать имя метода.
    String fileNamePrefix();
    //TODO 5.	Если мы сохраняем результат на диск, должна быть возможность указать, что данный файл надо дополнительно
    // сжимать в zip архив.
    boolean zip() default false;
    //TODO 2.	Возможность указывать, какие аргументы метода учитывать при определении уникальности результата, а какие
    // игнорировать(по умолчанию все аргументы учитываются). Например, должна быть возможность указать, что doHardWork()
    // должен игнорировать значение второго аргумента, уникальность определяется только по String аргументу.
    //    double r1 = service.doHardWork("work1", 10); //считает результат
    //    double r2 = service.doHardWork("work1", 5);  // результат из кеша, несмотря на то что  второй аргумент различается
    Class<?>[] identityBY();
    //TODO 3.	Если возвращаемый тип это List – возможность указывать максимальное количество элементов в нем. То есть,
    // если нам возвращается List с size = 1млн, мы можем сказать что в кеше достаточно хранить 100т элементов.
    long listMaxSize();
}

