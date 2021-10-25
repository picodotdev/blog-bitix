package io.github.picodotdev.blogbitix.annotationprocessor;

...

public class ValueProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_11;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Value.class.getName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<Element> annotatedElements = new HashSet<>();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = environment.getElementsAnnotatedWith(annotation);
            annotatedElements.addAll(elements);
        }

        for (Element element : annotatedElements) {
            try {
                List<? extends Element> executableEmentls = element.getEnclosedElements().stream().filter(t -> {
                    return t.getKind().equals(ElementKind.METHOD);
                }).collect(Collectors.toList());
                boolean hasHashCode = executableEmentls.stream().anyMatch(e -> {
                    return e.getSimpleName().toString().equals("hashCode");
                });
                boolean hasEquals = executableEmentls.stream().anyMatch(e -> {
                    return e.getSimpleName().toString().equals("equals");
                });
                boolean hasToString = executableEmentls.stream().anyMatch(e -> {
                    return e.getSimpleName().toString().equals("toString");
                });

                if (!hasHashCode || !hasEquals || !hasToString) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Class " + element.getSimpleName() + " should override hashCode, equals and toString methods");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
