package com.baimi.init.vo;

import com.baimi.init.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zhang
 * DATE 2024/6/21 下午2:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationVO {
    private String username;
    private String remark;
    private String type;
    private LocalDateTime modifyTime;
    public OperationVO(Operation operation) {
        this.modifyTime = operation.getModifyTime();
        this.remark = operation.getRemark();
        this.type = operation.getType();
    }
}
