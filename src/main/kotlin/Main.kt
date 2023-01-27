/*
目的:２列のファイルから４択を作り出すプログラムを作成
要素は6つ
左から、問題、選択肢1,選択肢2、選択肢3,選択肢4,正解
区切り文字はカンマ
 */

import java.io.*

//データクラスのrandom_numberを作成。要素は4つ
data class random_number(
    var first_choice: Int,
    var second_choice: Int,
    var third_choice: Int,
    var fourth_choice: Int
)

fun main(){

    val listsmp = rfile()//listsmp に rfileによって読み込んだList<String>型のデータを格納
    //println(listsmp.size)

    //jogai : 正解の問題番号、つまり乱数生成時に除外する番号
    for(jogai in 0 until listsmp.size){
        val list_component = make_random(jogai,listsmp.size)
        val list_component1 = list_component.component1()
        val list_component2 = list_component.component2()
        val list_component3 = list_component.component3()
        val list_component4 = list_component.component4()

        val list = List(listsmp.size) { MutableList(2) { "0" } }
        repeat(list.size) { i ->
            val (x,y) = listsmp[i].split(" ").map { it }
            list[i][0] = x
            list[i][1] = y
        }


        wfileenter(list[list_component1][0] + "," + list[list_component1][1] + "," + list[list_component2][1] + "," + list[list_component3][1]
                + "," + list[list_component4][1] + "," + list[list_component1][1])
    }

}
//ファイルを一行ごとに読み込んでList<String>型で返す。区切りの処理はしていない。
private fun rfile(): List<String> {
    val modoris = mutableListOf<String>()//modoris は戻り値になるList<String>を収納する変数
    val filenamer = "nameofanimal.txt"
    File(filenamer).forEachLine { modoris.add(it) }
    return modoris
}

// 正解以外の問題番号を乱数として生成
// m:正解の問題番号、n:乱数を生成する上限値
private fun make_random(m: Int, n: Int): random_number {
    val randomNumbers = (0 until n).shuffled().filter { it != m }.take(4)
    val rn_made = random_number(randomNumbers[0],randomNumbers[1],randomNumbers[2],randomNumbers[3])
    return rn_made
}

//改行してファイルに書き込む
private fun wfileenter(x : String){
    val fil = FileWriter("wfileSampleTrash.txt", true)
    val pw = PrintWriter(BufferedWriter(fil))
    pw.println(x)
    pw.close()
}