package com.github.songgyubin.cleanarchitecturecoretemplateplugin.enums

/**
 * 각 레이어 타입
 *
 * @author   Gyub
 * @created  2024/04/11
 */
enum class LayerType(val displayName: String) {
    DATA("Data Layer"),
    DOMAIN("Domain Layer"),
    HILT("Apply Hilt")
}