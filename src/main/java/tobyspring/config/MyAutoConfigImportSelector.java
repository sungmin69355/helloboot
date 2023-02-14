package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //META-INF/spring/full-qualified-annotation-name.imports 안에서 읽어온다.
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(),false).toArray(String[]::new);
    }
}
