package com.baimi.init.common.idempotent;

import com.baimi.init.common.context.ApplicationContextHolder;
import com.baimi.init.common.enums.SceneEnum;
import com.baimi.init.common.enums.TypeEnum;
import com.baimi.init.common.handler.IdempotentHandler;
import com.baimi.init.common.handler.IdempotentMQHandler;
import com.baimi.init.common.handler.IdempotentSpELHandler;

/**
 * @author zhang
 * @description
 * @since 2024/8/7 下午3:25
 */
public final class IdempotentHandlerFactory {

    /**
     * 获取幂等执行处理器
     *
     * @param scene 指定幂等验证场景类型
     * @param type  指定幂等处理类型
     * @return 幂等执行处理器
     */
    public static IdempotentHandler getInstance(SceneEnum scene, TypeEnum type) {
        IdempotentHandler result = null;
        switch (scene) {
            case RESTAPI:
                switch (type) {
                    case PARAM:
                        result = ApplicationContextHolder.getBean(IdempotentParamService.class);
                        break;
                    case TOKEN:
                        result = ApplicationContextHolder.getBean(IdempotentTokenService.class);
                        break;
                    case SPEL:
                        result = ApplicationContextHolder.getBean(IdempotentSpELHandler.class);
                        break;
                    default:
                        break;
                }
                break;
            case MQ:
                result = ApplicationContextHolder.getBean(IdempotentMQHandler.class);
                break;
            default:
                break;
        }
        return result;
    }
}
