package cc.rits.openhacku2022.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ファイルモデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {

    /**
     * UUID
     */
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    /**
     * ファイルのバイナリ
     */
    private byte[] content;

}
