package com.github.songgyubin.cleanarchitecturecoretemplateplugin.content

/**
 * 파일 내용 생성 팩토리
 *
 * @author   Gyub
 * @created  2024/04/27
 */
object FileContentFactory {
    fun createFileContent(fileCategory: FileCategory, isApplyHilt: Boolean, prefix: String):String {
        return when (fileCategory) {
            FileCategory.DTO -> getDTOFileContent(prefix)
            FileCategory.DataSource -> getDataSourceFileContent(isApplyHilt, prefix)
            FileCategory.Entity -> getEntityFileContent(prefix)
            FileCategory.Repository -> getRepositoryFileContent(prefix)
            FileCategory.RepositoryImpl -> getRepositoryImplFileContent(isApplyHilt, prefix)
            FileCategory.UseCase -> getUseCaseFileContent(isApplyHilt, prefix)
        }
    }

    private fun getRepositoryFileContent(prefix: String) = "interface ${prefix}Repository"

    private fun getRepositoryImplFileContent(isApplyHilt: Boolean, prefix: String) =
        if (!isApplyHilt) "class ${prefix}RepositoryImpl: ${prefix}Repository"
        else """
            import javax.inject.Inject
        
        class ${prefix}RepositoryImpl: ${prefix}Repository"
    """

    private fun getDataSourceFileContent(isApplyHilt: Boolean, prefix: String) =
        if (!isApplyHilt) "class ${prefix}DataSource"
        else """
            import javax.inject.Inject
        
    """

    private fun getUseCaseFileContent(isApplyHilt: Boolean, prefix: String) =
        if (!isApplyHilt) "class ${prefix}UseCase"
        else """
            import javax.inject.Inject
        
    """

    private fun getEntityFileContent(prefix: String) = "data class $prefix()"

    private fun getDTOFileContent(prefix: String) = "data class $prefix()"
}