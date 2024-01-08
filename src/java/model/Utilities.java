package model;

public class Utilities {

  /**
   * レコード数を受け取って､12件毎のページ遷移する為のパーツを生成する
   *
   * @param category 取得するレシピの種類
   * @param searchParam 検索条件
   * @param sort 取得するレシピの順序
   * @param count 上記のパラメータで絞り込まれたレシピの数
   * @return 生成したページ遷移用パーツ
   */
  public static String editPages(String category,String searchParam,int count,String sort){

    String pages = "<div class=\"pagenation-box\">"; //div開始タグで始まり

    //↓わざと条件をtrueにして､ﾙｰﾌﾟ内にbreakがある事を暗示している
    //　(ﾍﾟｰｼﾞﾊﾟｰﾂの最期に"/"を付けないようにする為､ﾙｰﾌﾟの途中で抜ける)
    for(int i=1; true; i++){

      //a開始タグ & 基本URL
      pages += ("<a href=\"/HealthyRecipeProject/category?");

      if(category!=null){ pages += ("category="+Esc.html(category)+"&"); } // Esc.html()はエスケープ処理メソッド

      if(searchParam!=null){ pages += ("keyword="+Esc.html(searchParam)+"&"); }

      if(sort!=null){ pages += ("sort="+Esc.html(sort)+"&"); }

      //pageパラメータ
      pages += ("page="+i);

      //a開始タグ閉じ括弧 & 表示用ページNo & a閉じタグ
      pages += ("\"><div class=\"pagenation\"><p>"+i+"</p></div></a>");

      //ループ抜け判定
      if(i*12>=count){ break; }
    }

    return pages+="</div>"; //div閉じタグで終わる

  }
}