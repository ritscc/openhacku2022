package cc.rits.openhacku2022.util;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.InternalServerErrorException;
import cc.rits.openhacku2022.model.FileModel;
import cc.rits.openhacku2022.property.GcpProperty;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ファイルストレージユーティリティ
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FileStorageUtil {

    private final GcpProperty gcpProperty;

    /**
     * ファイルをアップロード
     *
     * @param fileModel ファイル
     */
    public void upload(final FileModel fileModel) {
        if (!this.gcpProperty.getCloudStorage().isEnabled()) {
            return;
        }

        final var storage = StorageOptions.newBuilder().setProjectId(gcpProperty.getProjectId()).build().getService();
        try {
            // TODO: アップロードフォルダを指定
            final var blobId = BlobId.of(gcpProperty.getCloudStorage().getBucketName(), fileModel.getUuid());
            final var blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, fileModel.getContent());
        } catch (final StorageException e) {
            log.error(e.getMessage(), e);
            throw new InternalServerErrorException(ErrorCode.FAILED_TO_UPLOAD_FILE);
        }
    }

}
