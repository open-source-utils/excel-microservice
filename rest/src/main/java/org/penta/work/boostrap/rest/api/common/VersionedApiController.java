package org.penta.work.boostrap.rest.api.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RestController
@RequestMapping("${v1API}")
public @interface VersionedApiController {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
