package com.github.songgyubin.cleanarchitecturecoretemplateplugin.wizard

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.LabelWidget
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.template
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateDataSource
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateEntity
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateInject
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateModel
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateRepository
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateRepositoryImpl
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateUseCase
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.prefix
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileCategory.DTO
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileCategory.DataSource
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileCategory.Entity
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileCategory.Repository
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileCategory.RepositoryImpl
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileCategory.UseCase
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.content.FileContentFactory.createFileContent
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.enums.LayerType
import java.io.File

/**
 * 파일 템플릿 생성기
 *
 * @author   Gyub
 * @created  2024/04/11
 */
class GenerateFileTemplateProvider : WizardTemplateProvider() {
    /**
     * 파일 템플릿 리스트 반환
     *
     * @return 커스텀한 파일 템플릿
     */
    override fun getTemplates(): List<Template> {
        return listOf(fileTemplate)
    }

    private val fileTemplate: Template
        get() = template {
            name = "Clean Architecture Core Generator"
            description = "Generate Clean Architecture components"
            minApi = 21
            category = Category.Other
            formFactor = FormFactor.Mobile
            screens = listOf(WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

            recipe = { data: TemplateData ->
                val moduleData = data as ModuleTemplateData
                val srcDir = moduleData.srcDir
                val packageName = moduleData.packageName

                val createdFiles = generateFile(srcDir, packageName, prefix.value)

                createdFiles.forEach { file -> open(file) }
            }

            widgets(
                TextFieldWidget(prefix),
                LabelWidget(LayerType.DOMAIN.displayName),
                CheckBoxWidget(generateRepository),
                CheckBoxWidget(generateUseCase),
                CheckBoxWidget(generateEntity),
                LabelWidget(LayerType.DATA.displayName),
                CheckBoxWidget(generateRepositoryImpl),
                CheckBoxWidget(generateDataSource),
                CheckBoxWidget(generateModel),
                LabelWidget(LayerType.HILT.displayName),
                CheckBoxWidget(generateInject)
            )
        }

    /**
     * 사용자 행동에 따른 파일 생성
     *
     * @param srcDir 소스 디렉토리
     * @param packageName 패키지 명
     * @param prefix 입력받은 접두사
     * @return 사용자 행동에 따른 파일
     */
    private fun RecipeExecutor.generateFile(srcDir: File, packageName: String, prefix: String): MutableList<File> {
        val createdFiles = mutableListOf<File>()
        val isApplyHilt = generateInject.value

        val fileDetails = listOf(
            Triple(generateRepositoryImpl, "${prefix}RepositoryImpl.kt", createFileContent(RepositoryImpl, isApplyHilt = isApplyHilt, prefix = prefix)),
            Triple(generateDataSource, "${prefix}DataSource.kt", createFileContent(DataSource, isApplyHilt = isApplyHilt, prefix = prefix)),
            Triple(generateModel, "${prefix}.kt", createFileContent(DTO, isApplyHilt = isApplyHilt, prefix = prefix)),
            Triple(generateRepository, "${prefix}Repository.kt", createFileContent(Repository, isApplyHilt = isApplyHilt, prefix = prefix)),
            Triple(generateUseCase, "${prefix}UseCase.kt", createFileContent(UseCase, isApplyHilt = isApplyHilt, prefix = prefix)),
            Triple(generateEntity, "${prefix}Entity.kt", createFileContent(Entity, isApplyHilt = isApplyHilt, prefix = prefix))
        )

        fileDetails.forEach { (condition, fileName, content) ->
            if (condition.value) {
                val filePath = srcDir.resolve(fileName)
                val fileContent = "package $packageName\n\n$content"
                save(fileContent, filePath)
                createdFiles.add(filePath)
            }
        }

        return createdFiles
    }
}

