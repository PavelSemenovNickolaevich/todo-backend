package ru.java.backend.todo.todobackend.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Aspect
@Component
class LoggingAspect {

    companion object {
        val log: Logger = LogManager.getLogger(LoggingAspect::class.java.name)
    }

    //аспект будет выполняться для всех методов из пакета контроллеров
    @Around("execution(* ru.java.backend.todo.todobackend.controller..*(..)))")
    fun profileControllerMethods(proceedingJoinPoint: ProceedingJoinPoint): Any {
        val methodSignature = proceedingJoinPoint.signature as MethodSignature

        // получить информацию о том, какой класс и метод выполняется
        val className = methodSignature.declaringType.simpleName
        val methodName = methodSignature.name
        log.info("-------- Executing $className.$methodName   ----------- ")
        val countDown = StopWatch()

        //засекаем время
        countDown.start()
        val result = proceedingJoinPoint.proceed()
        countDown.stop()
        log.info("-------- Execution time of  " + className + "." + methodName + " :: " + countDown.totalTimeMillis + "ms  ----------- ")
        return result
    }
}