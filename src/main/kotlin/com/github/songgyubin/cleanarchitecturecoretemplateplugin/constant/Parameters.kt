package com.github.songgyubin.cleanarchitecturecoretemplateplugin.constant

import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.booleanParameter
import com.android.tools.idea.wizard.template.stringParameter

/**
 * 위젯에 필요한 파라미터들
 *
 * @author   Gyub
 * @created  2024/04/11
 */
object Parameters {

    val prefix = stringParameter {
        name = "Prefix"
        default = ""
        help = "접두사를 입력해 주세요."
        constraints = listOf(Constraint.NONEMPTY)
    }

    val generateRepositoryImpl = booleanParameter {
        name = "Repository Implementation"
        default = false
        help = "RepositoryImpl 클래스를 생성하시겠습니까?"
    }

    val generateDataSource = booleanParameter {
        name = "DataSource"
        default = false
        help = "DataSource 클래스를 생성하시겠습니까?"
    }

    val generateModel = booleanParameter {
        name = "DTO"
        default = false
        help = "DTO를 생성하시겠습니까?"
    }

    val generateRepository = booleanParameter {
        name = "Repository Interface"
        default = false
        help = "Repository 인터페이스를 생성하시겠습니까?"
    }

    val generateUseCase = booleanParameter {
        name = "UseCase"
        default = false
        help = "UseCase 클래스를 생성하시겠습니까?"
    }

    val generateEntity = booleanParameter {
        name = "Entity"
        default = false
        help = "Entity 데이터 클래스를 생성하시겠습니까?"
    }
}