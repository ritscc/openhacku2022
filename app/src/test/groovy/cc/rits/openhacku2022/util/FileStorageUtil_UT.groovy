package cc.rits.openhacku2022.util

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.InternalServerErrorException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.FileModel
import cc.rits.openhacku2022.property.GcpProperty
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * FileStorageUtilの単体テスト
 */
class FileStorageUtil_UT extends BaseSpecification {

    @Autowired
    FileStorageUtil sut

    @SpringBean
    GcpProperty gcpProperty = Mock()

    def "upload: GCP送信が抑制されている場合、アップロードされない"() {
        given:
        final fileModel = RandomHelper.mock(FileModel)

        final cloudStorageProperty = RandomHelper.mock(GcpProperty.CloudStorage)
        cloudStorageProperty.enabled = false

        when:
        this.sut.upload(fileModel)

        then:
        1 * this.gcpProperty.cloudStorage >> cloudStorageProperty
        noExceptionThrown()
    }

    def "upload: StorageExceptionが発生した場合は500エラー"() {
        given:
        final fileModel = RandomHelper.mock(FileModel)

        final cloudStorageProperty = RandomHelper.mock(GcpProperty.CloudStorage)
        cloudStorageProperty.enabled = true
        cloudStorageProperty.bucketName = RandomHelper.alphanumeric(10)

        when:
        this.sut.upload(fileModel)

        then:
        2 * this.gcpProperty.cloudStorage >> cloudStorageProperty
        1 * this.gcpProperty.projectId >> RandomHelper.alphanumeric(10)
        final BaseException exception = thrown()
        verifyException(exception, new InternalServerErrorException(ErrorCode.FAILED_TO_UPLOAD_FILE))
    }

}
