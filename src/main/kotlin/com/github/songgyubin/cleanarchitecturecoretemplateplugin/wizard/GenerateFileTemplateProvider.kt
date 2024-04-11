package com.github.songgyubin.cleanarchitecturecoretemplateplugin.wizard

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.LabelWidget
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.StringParameter
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.template
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.enums.LayerType
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateDataSource
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateEntity
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateModel
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateRepository
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateRepositoryImpl
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.generateUseCase
import com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant.Parameters.prefix
import java.io.File

/**
 * 파일 템플릿 생성기
 *
 * @author   Gyub
 * @created  2024/04/11
 */
class GenerateFileTemplateProvider : WizardTemplateProvider() {
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

                val createdFiles = generateFile(srcDir, packageName, prefix)

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
            )
        }

    /**
     * 사용자 행동에 따른 파일 생성
     *
     * @param srcDir 소스 디렉토리
     * @param packageName 패키지 명
     * @param prefix 입력받은 접두사
     * @return
     */
    private fun RecipeExecutor.generateFile(srcDir: File, packageName: String, prefix: StringParameter): MutableList<File> {
        val createdFiles = mutableListOf<File>()

        val fileDetails = listOf(
            Triple(generateRepositoryImpl, "${prefix.value}RepositoryImpl.kt", "class ${prefix.value}RepositoryImpl"),
            Triple(generateDataSource, "${prefix.value}DataSource.kt", "class ${prefix.value}DataSource"),
            Triple(generateModel, "${prefix.value}Model.kt", "data class ${prefix.value}Model()"),
            Triple(generateRepository, "${prefix.value}Repository.kt", "interface ${prefix.value}Repository"),
            Triple(generateUseCase, "${prefix.value}UseCase.kt", "class ${prefix.value}UseCase"),
            Triple(generateEntity, "${prefix.value}Entity.kt", "data class ${prefix.value}Entity()")
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

