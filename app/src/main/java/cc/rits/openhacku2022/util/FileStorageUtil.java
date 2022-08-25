package cc.rits.openhacku2022.util;

import java.io.FileInputStream;

import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.InternalServerErrorException;
import cc.rits.openhacku2022.model.FileModel;
import cc.rits.openhacku2022.property.GcpProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ファイルストレージユーティリティ
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FileStorageUtil {

    private static final String IMAGE_PATH = "images/";

    private final GcpProperty gcpProperty;

    /**
     * ファイルをアップロード
     *
     * @param fileModel ファイル
     * @return url
     */
    public String upload(final FileModel fileModel) {
        final var url = String.format("https://storage.googleapis.com/%s/images/%s", //
            this.gcpProperty.getCloudStorage().getBucketName(), //
            fileModel.getUuid());

        if (!this.gcpProperty.getCloudStorage().isEnabled()) {
            return url;
        }
        try {
            final var storage = StorageOptions.newBuilder() //
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(this.gcpProperty.getCredentialsPath()))) //
                .setProjectId(gcpProperty.getProjectId()) //
                .build().getService();

            final var blobId = BlobId.of(gcpProperty.getCloudStorage().getBucketName(), IMAGE_PATH + fileModel.getUuid());
            final var blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, fileModel.getContent());

            return url;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            throw new InternalServerErrorException(ErrorCode.FAILED_TO_UPLOAD_FILE);
        }
    }

}
