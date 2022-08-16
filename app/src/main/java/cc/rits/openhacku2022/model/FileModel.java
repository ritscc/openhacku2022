package cc.rits.openhacku2022.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
    String uuid = UUID.randomUUID().toString();

    /**
     * ファイルのバイナリ
     */
    byte[] content;

}
