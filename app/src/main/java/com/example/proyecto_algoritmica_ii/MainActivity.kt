package com.example.proyecto_algoritmica_ii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    val inf = 1e9.toInt()
    lateinit var editTextNumTareas: EditText
    lateinit var editTextTareas: EditText
    lateinit var editTextSource: EditText
    lateinit var editTextSink: EditText
    lateinit var editTextAristas: EditText
    lateinit var editTextDatosAristas: EditText
    lateinit var resultTextView: TextView
    lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumTareas = findViewById(R.id.editTextNumTareas)
        editTextTareas = findViewById(R.id.editTextTareas)
        editTextSource = findViewById(R.id.editTextSource)
        editTextSink = findViewById(R.id.editTextSink)
        editTextAristas = findViewById(R.id.editTextAristas)
        editTextDatosAristas = findViewById(R.id.editTextDatosAristas)
        resultTextView = findViewById(R.id.textViewResult)
        calculateButton = findViewById(R.id.buttonCalculate)

        calculateButton.setOnClickListener {
            val numTareas = editTextNumTareas.text.toString().toInt()
            val tareaData = editTextTareas.text.toString()
            val sourceTarea = editTextSource.text.toString()
            val sinkTarea = editTextSink.text.toString()
            val aristas = editTextAristas.text.toString().toInt()
            val aristasData = editTextDatosAristas.text.toString()

            val scannerTareas = Scanner(tareaData)
            val tareas = ArrayList<String>()
            val pos = HashMap<String, Int>()

            for (i in 0 until numTareas) {
                val tarea = scannerTareas.next()
                tareas.add(tarea)
                pos[tarea] = i
            }

            val sInicio = sourceTarea
            val tFinal = sinkTarea
            val s = pos[sInicio] ?: -1
            val t = pos[tFinal] ?: -1

            val grafo = Array(numTareas) { IntArray(numTareas) }
            val scannerAristas = Scanner(aristasData)

            for (i in 0 until aristas) {
                val fromTarea = scannerAristas.next()
                val toTarea = scannerAristas.next()
                val capacity = scannerAristas.nextInt()
                val from = pos[fromTarea] ?: -1
                val to = pos[toTarea] ?: -1

                if (from != -1 && to != -1) {
                    grafo[from][to] = capacity
                    grafo[to][from] = 0
                }
            }

            val maxFlow = maxFlow(grafo, s, t)
            resultTextView.text = "El flujo máximo es: $maxFlow"
        }
    }

    // Función para encontrar el flujo máximo en el grafo usando el algoritmo de Edmonds-Karp
    fun maxFlow(grafo: Array<IntArray>, source: Int, sink: Int): Int {
        val N = grafo.size
        val parent = IntArray(N)

        var maxFlow = 0
        while (true) {
            Arrays.fill(parent, -1)
            val q: Queue<Int> = LinkedList()
            q.add(source)
            parent[source] = source

            while (q.isNotEmpty() && parent[sink] == -1) {
                val u = q.poll()
                for (v in 0 until N) {
                    if (parent[v] == -1 && grafo[u][v] > 0) {
                        parent[v] = u
                        q.add(v)
                    }
                }
            }

            if (parent[sink] == -1) {
                break
            }

            var minCapacity = inf
            var cur = sink
            while (cur != source) {
                val prev = parent[cur]
                minCapacity = min(minCapacity, grafo[prev][cur])
                cur = prev
            }

            cur = sink
            while (cur != source) {
                val prev = parent[cur]
                grafo[prev][cur] -= minCapacity
                grafo[cur][prev] += minCapacity
                cur = prev
            }

            maxFlow += minCapacity
        }

        return maxFlow
    }
}