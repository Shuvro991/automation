package com.yourname.autoflowai.ml

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ModelManager {
    private val instructionLLM = InstructionLLM()
    private val multimodalLLM = MultimodalLLM()
    
    suspend fun processInstruction(input: String): String {
        return withContext(Dispatchers.IO) {
            instructionLLM.process(input)
        }
    }
    
    suspend fun processMultimodal(input: String): String {
        return withContext(Dispatchers.IO) {
            multimodalLLM.process(input)
        }
    }
    
    fun isModelReady(modelType: String): Boolean {
        return when (modelType) {
            "instruction" -> instructionLLM.isReady()
            "multimodal" -> multimodalLLM.isReady()
            else -> false
        }
    }
    
    suspend fun initializeModels(context: Context) {
        withContext(Dispatchers.IO) {
            instructionLLM.initialize(context)
            multimodalLLM.initialize(context)
        }
    }
}