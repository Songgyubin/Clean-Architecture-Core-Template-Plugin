package com.github.songgyubin.cleanarchitecturecoretemplateplugin.content

/**
 * 생성될 파일 종류
 *
 * @author   Gyub
 * @created  2024/04/27
 */
sealed interface FileCategory {
    data object Repository: FileCategory
    data object RepositoryImpl: FileCategory
    data object UseCase: FileCategory
    data object DataSource: FileCategory
    data object DTO: FileCategory
    data object Entity: FileCategory
}