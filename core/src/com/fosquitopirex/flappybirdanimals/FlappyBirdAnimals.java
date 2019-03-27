package com.fosquitopirex.flappybirdanimals;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

//ca-app-pub-8030976745457467~8782455883
//ca-app-pub-8030976745457467/5859164679

public class FlappyBirdAnimals extends ApplicationAdapter implements VideoEventListener, ApplicationListener, GestureDetector.GestureListener {
	private SpriteBatch batch;
	private float width;
	private float height;
	private float velocidadey;
	private float posicaoy;
	private Texture canoBaixo;
	private Texture canoTopo;
	private float posicaoCanoX;
	private float espacoCanos;
	private Random numRandom;
	private float posicaoCanoY;
	private int  estadoJogo=0;
	private float gameVelocity=0;
	private BitmapFont font;
	private int pontuacao=0;
	private boolean marcarPonto=false;
	private Circle passaroCirulo;
	private Rectangle canoTopoRetangulo;
	private Rectangle canoBaixoRetangulo;
	private Rectangle groundRetangulo;
	private Texture gameOver;
	private BitmapFont mensagem;
	private String msg;
	private GlyphLayout glyphLayoutRestart;
    private GlyphLayout glyphLayoutScore;
    private GlyphLayout glyphLayoutBest;
    private GlyphLayout glyphLayoutNew;
    private GlyphLayout glyphLayoutAnimal;
    private Texture scoreBox;
    private float posicaoScoreBoxY;
    private static final String STORE = "Store";
    private int bestScore;
    private boolean best = false;
    private Texture botaoRestart;
    private Texture botaoShop;
    private Texture botaoDouble;
    private Texture coin;
    private boolean winCoins = false;
    private Rectangle restart;
    private Vector3 res;
    private Texture storeBackground;
    private Rectangle shop;
    private Rectangle doubleCoins;
    private Texture shopTitle;
    private Texture[] animal = new Texture[15];
    private Texture[] fundo = new Texture[4];
    private Texture[] chao = new Texture[4];
    private String[] nomeAnimal = new String[15];
    private int[] comprarAnimal = new int[14];
    private int[] comprarFundo = new int[3];
    private Texture botaoComprar;
    private Rectangle buyAnimal;
    private Rectangle buyFundo;
    private int coins;
    private int indexAnimal;
    private int indexFundo;
    private Preferences storage;
    AdHandler handler;
    public GoogleServices googleServices;
    private Boolean vel = true;
    private ActionResolver actionResolver;
    private Texture setaEsquerda;
    private Texture setaDireita;
    private int actualIndexAnimal;
    private int actualIndexFundo;
    private Rectangle setaBuyAnimalEsquerda;
    private Rectangle setaBuyAnimalDireita;
    private Rectangle setaBuyFundoEsquerda;
    private Rectangle setaBuyFundoDireita;

    public FlappyBirdAnimals(AdHandler handler,GoogleServices googleServices, ActionResolver actionResolver){
        this.handler = handler;
        this.googleServices = googleServices;
        this.googleServices.setVideoEventListener(this);
        this.actionResolver = actionResolver;
        actionResolver.showOrLoadInterstital();
    }

	@Override
	public void create () {
        Gdx.input.setCatchBackKey(true);
		batch = new SpriteBatch();
		fundo[0] = new Texture("fundo1.png");
        fundo[1] = new Texture("fundo2.png");
        fundo[2] = new Texture("fundo3.png");
        fundo[3] = new Texture("fundo1.png");
		chao[0] = new Texture("ground1.png");
        chao[1] = new Texture("ground1.png");
        chao[2] = new Texture("ground2.png");
        chao[3] = new Texture("ground1.png");
		velocidadey=-height/100;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
        posicaoy = height/2;
        canoBaixo = new Texture("cano_baixo_maior.png");
        canoTopo = new Texture("cano_topo_maior.png");
        posicaoCanoX = width+200;
        espacoCanos = height/6;
        numRandom = new Random();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
	    font.getData().setScale(6);
	    passaroCirulo = new Circle();
	    canoTopoRetangulo = new Rectangle();
	    canoBaixoRetangulo = new Rectangle();
	    groundRetangulo = new Rectangle();
	    gameOver = new Texture("game_over.png");
	    mensagem = new BitmapFont();
	    mensagem.setColor(Color.WHITE);
	    mensagem.getData().setScale(3);
	    msg = "Click to start";
	    glyphLayoutRestart = new GlyphLayout();
        glyphLayoutScore = new GlyphLayout();
        glyphLayoutBest = new GlyphLayout();
        glyphLayoutNew = new GlyphLayout();
        glyphLayoutAnimal = new GlyphLayout();
        scoreBox = new Texture("score_box.png");
        posicaoScoreBoxY = height;
        botaoRestart = new Texture("botao_restart.png");
        botaoShop = new Texture("botao_shop.png");
        botaoDouble = new Texture("botao_double.png");
        coin = new Texture("Coin.png");
        restart = new Rectangle();
        res = new Vector3();
        storeBackground = new Texture("store_background.png");
        shop = new Rectangle();
        doubleCoins = new Rectangle();
        shopTitle = new Texture("shop.png");
        animal[0] = new Texture("passaro.png");
        animal[1] = new Texture("porco.png");
        animal[2] = new Texture("abelha.png");
        animal[3] = new Texture("elefante.png");
        animal[4] = new Texture("peixe.png");
        animal[5] = new Texture("galinha.png");
        animal[6] = new Texture("TV.png");
        animal[7] = new Texture("tubarao.png");
        animal[8] = new Texture("dragao.png");
        animal[9] = new Texture("tartaruga.png");
        animal[10] = new Texture("panda.png");
        animal[11] = new Texture("esquilo.png");
        animal[12] = new Texture("pato.png");
        animal[13] = new Texture("carro.png");
        animal[14] = new Texture("passaro.png");
        nomeAnimal[0] = "Bird";
        nomeAnimal[1] = "Pig";
        nomeAnimal[2] = "Bee";
        nomeAnimal[3] = "Elephant";
        nomeAnimal[4] = "Fish";
        nomeAnimal[5] = "Chicken";
        nomeAnimal[6] = "TV";
        nomeAnimal[7] = "Shark";
        nomeAnimal[8] = "Dragon";
        nomeAnimal[9] = "Turtle";
        nomeAnimal[10] = "Panda";
        nomeAnimal[11] = "Squirrel";
        nomeAnimal[12] = "Duck";
        nomeAnimal[13] = "Car";
        nomeAnimal[14] = "Bird";
        comprarAnimal[0] = 150;
        comprarAnimal[1] = 400;
        comprarAnimal[2] = 1000;
        comprarAnimal[3] = 2000;
        comprarAnimal[4] = 5000;
        comprarAnimal[5] = 15000;
        comprarAnimal[6] = 50000;
        comprarAnimal[7] = 100000;
        comprarAnimal[8] = 200000;
        comprarAnimal[9] = 500000;
        comprarAnimal[10] = 1000000;
        comprarAnimal[11] = 2000000;
        comprarAnimal[12] = 5000000;
        comprarAnimal[13] = 10000000;
        comprarFundo[0] = 5000;
        comprarFundo[1] = 50000;
        comprarFundo[2] = 500000;
        botaoComprar = new Texture("botao_comprar.png");
        buyAnimal = new Rectangle();
        buyFundo = new Rectangle();
        storage = Gdx.app.getPreferences(STORE);
        coins = storage.getInteger("coins");
        indexAnimal = storage.getInteger("indexAnimal");
        indexFundo = storage.getInteger("indexFundo");
        actualIndexAnimal = storage.getInteger("actualIndexAnimal");
        actualIndexFundo = storage.getInteger("actualIndexFundo");
        /*indexAnimal = 0;
        indexFundo = 0;
        actualIndexAnimal = 0;
        actualIndexFundo = 0;
        storage.putInteger("indexAnimal",indexAnimal);
        storage.putInteger("actualIndexAnimal",indexFundo);
        storage.putInteger("indexFundo",actualIndexFundo);
        storage.putInteger("actualIndexFundo",actualIndexAnimal);*/
        setaEsquerda = new Texture("seta_esquerda.png");
        setaDireita = new Texture("seta_direita.png");
        setaBuyAnimalEsquerda = new Rectangle();
        setaBuyAnimalDireita = new Rectangle();
        setaBuyFundoEsquerda = new Rectangle();
        setaBuyFundoDireita = new Rectangle();
	}

    public void onRewardedEvent(int coins, int pontuacao) {
        this.coins = this.coins + this.pontuacao;
        storage.putInteger("coins",this.coins);
        vel = false;
    }

    public void onRewardedVideoAdLoadedEvent() {
        if(this.googleServices.hasVideoLoaded() && vel) {
            this.googleServices.showRewardedVideoAd();
        }
    }

    @Override
    public void onRewardedVideoAdClosedEvent() {

    }

	@Override
	public void render () {
        if(estadoJogo==4)handler.showBanner(false);
        else handler.showBanner(true);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float deltaTime = Gdx.graphics.getDeltaTime();
        glyphLayoutRestart.setText(font,msg);
        glyphLayoutScore.setText(font,"Score");
        glyphLayoutBest.setText(font,"Best");
        glyphLayoutNew.setText(font,"NEW");
        glyphLayoutAnimal.setText(font,nomeAnimal[actualIndexAnimal]);
		if(estadoJogo==0){
            msg = "Click to start";
            glyphLayoutRestart.setText(font,msg);
		    if(Gdx.input.justTouched()){
		        estadoJogo=1;
            }
            velocidadey=-height/100;
        }else {
		    if(estadoJogo==1){
                velocidadey += height/2000;
                if(posicaoy > height){
                    posicaoy = height;
                    velocidadey = 0;
                }
                if (gameVelocity < 6) gameVelocity += 0.0004;
                if (posicaoy > 0 || velocidadey < 0) posicaoy -= velocidadey;
                posicaoCanoX -= deltaTime * width/2 + gameVelocity;
                if (Gdx.input.justTouched()) {
                    velocidadey = -height/100;
                }
                if (posicaoCanoX < -height/7) {
                    posicaoCanoX = width;
                    marcarPonto = false;
                    posicaoCanoY = numRandom.nextInt(2*(int)height/5) - height/7;
                }
                if (posicaoCanoX < width / 11) {
                    if (!marcarPonto) {
                        pontuacao = pontuacao + (actualIndexAnimal+1)*(actualIndexFundo*2+1);
                        marcarPonto = true;
                    }
                }
            }else if(estadoJogo==2){
                if(Gdx.input.justTouched()){
                    restart = new Rectangle(width/2-width/4,height/7+height/20,width/2,height/12);
                    shop = new Rectangle(width/2-width/4,height/4-height/50+height/20,width/4-width/25,height/13);
                    doubleCoins = new Rectangle(width/2+width/40,height/4-height/50+height/20,width/4-width/25,height/13);
                    res.set(Gdx.input.getX(),height-Gdx.input.getY(),0);
                    if(restart.contains(res.x,res.y)){
                        estadoJogo=0;
                        best=false;
                        vel=true;
                        marcarPonto=false;
                        pontuacao=0;
                        velocidadey=-height/100;
                        posicaoy=height/2;
                        posicaoCanoX=width+200;
                        gameVelocity=0;
                        posicaoScoreBoxY=height;
                        winCoins=false;
                    }
                    if(shop.contains(res.x,res.y)){
                        actionResolver.showOrLoadInterstital();
                        estadoJogo=4;
                    }
                    if(doubleCoins.contains(res.x,res.y)){
                        onRewardedVideoAdLoadedEvent();
                    }
                }
            }else if(estadoJogo==3){
		        bestScore = storage.getInteger("bestScore");
		        if(!winCoins){
                    coins += pontuacao;
                    storage.putInteger("coins",coins);
                    storage.flush();
                    winCoins=true;
                }
		        if(pontuacao > bestScore){
		            best = true;
		            bestScore=pontuacao;
		            storage.putInteger("bestScore",bestScore);
                    storage.flush();
                }
                posicaoScoreBoxY -= deltaTime*height;
                if(posicaoScoreBoxY<=0) {
                    estadoJogo = 2;
                }
            }else if(estadoJogo==4){
                if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
                    estadoJogo=2;
                }
                else if(Gdx.input.justTouched()){
                    buyAnimal = new Rectangle(width/2+width/10,height/2+height/5-height/15 , width/4, height/15);
                    buyFundo = new Rectangle(width/2+width/10,height/8+height/5, width/4, height/15);
                    res.set(Gdx.input.getX(),height-Gdx.input.getY(),0);
                    if(buyAnimal.contains(res.x,res.y)){
                        if(coins>comprarAnimal[indexAnimal]){
                            actionResolver.showOrLoadInterstital();
                            coins -= comprarAnimal[indexAnimal];
                            indexAnimal++;
                            actualIndexAnimal = indexAnimal;
                            storage.putInteger("indexAnimal", indexAnimal);
                            storage.putInteger("actualIndexAnimal", actualIndexAnimal);
                            storage.putInteger("coins", coins);
                        }
                    }
                    else if(buyFundo.contains(res.x,res.y)){
                        if(coins>comprarFundo[indexFundo]){
                            actionResolver.showOrLoadInterstital();
                            coins-=comprarFundo[indexFundo];
                            indexFundo++;
                            actualIndexFundo = indexFundo;
                            storage.putInteger("indexFundo", indexFundo);
                            storage.putInteger("actualIndexFundo", actualIndexFundo);
                            storage.putInteger("coins", coins);
                        }
                    }
                    else {
                        if(actualIndexAnimal>0) {
                            setaBuyAnimalEsquerda = new Rectangle(width / 15, height / 2 + height / 7, width / 13, height / 22);
                        }
                        if(actualIndexAnimal<indexAnimal) {
                            setaBuyAnimalDireita = new Rectangle(width / 3 + width / 9, height / 2 + height / 7, width / 13, height / 22);
                        }
                        if(actualIndexFundo>0) {
                            setaBuyFundoEsquerda = new Rectangle(width / 15, height / 4 - height / 25, width / 13, height / 22);
                        }
                        if(actualIndexFundo<indexFundo) {
                            setaBuyFundoDireita = new Rectangle(width / 3 + width / 9, height / 4 - height / 25, width / 13, height / 22);
                        }
                        if(setaBuyAnimalEsquerda.contains(res.x,res.y) && actualIndexAnimal>0){
                            actualIndexAnimal--;
                            storage.putInteger("actualIndexAnimal", actualIndexAnimal);
                        }
                        else if(setaBuyAnimalDireita.contains(res.x,res.y) && actualIndexAnimal<indexAnimal){
                            actualIndexAnimal++;
                            storage.putInteger("actualIndexAnimal", actualIndexAnimal);
                        }
                        else if(setaBuyFundoEsquerda.contains(res.x,res.y) && actualIndexFundo>0){
                            actualIndexFundo--;
                            storage.putInteger("actualIndexFundo", actualIndexFundo);
                        }
                        else if(setaBuyFundoDireita.contains(res.x,res.y) && actualIndexFundo<indexFundo){
                            actualIndexFundo++;
                            storage.putInteger("actualIndexFundo", actualIndexFundo);
                        }
                    }
                }
            }
        }
        batch.begin();
		if(estadoJogo!=4) {
            batch.draw(fundo[actualIndexFundo], 0, 0, width, height);
            batch.draw(canoTopo, posicaoCanoX, height / 2 + espacoCanos / 2 + posicaoCanoY, width / 7, 2 * height / 3);
            batch.draw(canoBaixo, posicaoCanoX, height / 2 - 2 * height / 3 - espacoCanos / 2 + posicaoCanoY, width / 7, 2 * height / 3);
            batch.draw(chao[actualIndexFundo], 0, 0, width, height / 8);
            batch.draw(animal[actualIndexAnimal], height / 11, posicaoy, width / 10, height / 27);
            batch.draw(coin,width/100,height-height/40,width/25,height/50);
            font.getData().setScale(width/500);
            font.draw(batch,String.valueOf(coins),width/100+width/20,height-height/80);
        }
        if(estadoJogo==0){
            mensagem.getData().setScale(3);
            font.setColor(Color.WHITE);
            font.getData().setScale(6);
            mensagem.draw(batch,msg,width/2-glyphLayoutRestart.width/4,height/2-mensagem.getLineHeight()-50);
            font.draw(batch, String.valueOf(pontuacao), width / 2, height - height / 10);
        }else if(estadoJogo==1) {
            font.setColor(Color.WHITE);
            font.getData().setScale(6);
            font.draw(batch, String.valueOf(pontuacao), width / 2, height - height / 10);
        }else if(estadoJogo==2) {
            mensagem.getData().setScale(2);
            batch.draw(scoreBox,0,0+height/20, width,height);
            batch.draw(gameOver, width / 4, height/2 + height/12+height/20, width/2, height/10);
            font.getData().setScale(height/500);
            font.setColor(new Color(0x7CFC00FF));
            font.draw(batch,"Best", width/3-glyphLayoutBest.width/2, height/2-height/12+height/20);
            font.draw(batch,"Score", width-width/3-glyphLayoutScore.width/2, height/2-height/12+height/20);
            font.setColor(Color.WHITE);
            font.draw(batch,"" + String.valueOf(bestScore), width/3-glyphLayoutBest.width/2, height/2-height/8+height/20);
            if(best){
                font.setColor(Color.YELLOW);
                font.getData().setScale(height/800);
                font.draw(batch,"NEW", width/2-glyphLayoutNew.width/2, height/2-height/12+height/20);
                font.setColor(Color.WHITE);
                font.getData().setScale(height/500);
            }
            font.draw(batch,"" + String.valueOf(pontuacao), width-width/3-glyphLayoutScore.width/2, height/2-height/8+height/20);
            batch.draw(botaoRestart,width/2-width/4,height/7+height/20,width/2,height/12);
            batch.draw(botaoShop,width/2-width/4,height/4-height/50+height/20,width/4-width/25,height/13);
            batch.draw(botaoDouble,width/2+width/40,height/4-height/50+height/20,width/4-width/25,height/13);
        }else if(estadoJogo==3){
            mensagem.getData().setScale(2);
            batch.draw(scoreBox,0,posicaoScoreBoxY+height/20, width,height);
            batch.draw(gameOver, width / 4, posicaoScoreBoxY+height/2 + height/12+height/20, width/2,height/10);
            font.getData().setScale(height/500);
            font.setColor(new Color(0x7CFC00FF));
            font.draw(batch,"Best", width/3-glyphLayoutBest.width/2, posicaoScoreBoxY+height/2-height/12+height/20);
            font.draw(batch,"Score", width-width/3-glyphLayoutScore.width/2, posicaoScoreBoxY+height/2-height/12+height/20);
            font.setColor(Color.WHITE);
            font.draw(batch,"" + String.valueOf(bestScore), width/3-glyphLayoutBest.width/2, posicaoScoreBoxY+height/2-height/8+height/20);
            if(best){
                font.setColor(Color.YELLOW);
                font.getData().setScale(height/800);
                font.draw(batch,"NEW", width/2-glyphLayoutNew.width/2, posicaoScoreBoxY+height/2-height/12+height/20);
                font.setColor(Color.WHITE);
                font.getData().setScale(height/500);
            }
            font.draw(batch,"" + String.valueOf(pontuacao), width-width/3-glyphLayoutScore.width/2, posicaoScoreBoxY+height/2-height/8+height/20);
            batch.draw(botaoRestart,width/2-width/4,posicaoScoreBoxY+height/7+height/20,width/2,height/12);
            batch.draw(botaoShop,width/2-width/4,posicaoScoreBoxY+height/4-height/50+height/20,width/4-width/25,height/13);
            batch.draw(botaoDouble,width/2+width/40,posicaoScoreBoxY+height/4-height/50+height/20,width/4-width/25,height/13);
        }
        else if(estadoJogo==4){
            batch.draw(storeBackground,0,0,width,height);
            batch.draw(coin,width/25,height-height/25,width/25,height/50);
            font.getData().setScale(width/500);
            font.setColor(Color.BLACK);
            font.draw(batch,String.valueOf(coins),width/25+width/20,height-height/40);
            batch.draw(shopTitle,width/2-(3*width/8), height-height/6, width-width/4, height/10);
            batch.draw(animal[actualIndexAnimal],width/4-width/10+width/25,height/2+height/5-height/15,2*(width/10), 2*(height/27));
            font.getData().setScale(width/200);
            font.draw(batch,nomeAnimal[actualIndexAnimal],width/4+width/25-glyphLayoutAnimal.width/2,height/2+height/6-height/15);
            font.getData().setScale(width/500);
            font.draw(batch,String.valueOf(actualIndexAnimal+1)+"x",width/3+width/25,height/2+height/5-height/15);
            if(indexAnimal<14){
                batch.draw(botaoComprar,width/2+width/10,height/2+height/5-height/15 , width/4, height/15);
                batch.draw(coin,width/2+width/11,height/2+height/7+height/64-height/15,width/20,height/40);
                font.getData().setScale(width/300);
                font.draw(batch,String.valueOf(comprarAnimal[indexAnimal]),width/2+width/7,height/2+height/6+height/64-height/15);
            }
            batch.draw(fundo[actualIndexFundo],width/4-width/6+width/80,height/10,width/3+width/15,height/3);
            batch.draw(chao[actualIndexFundo],width/4-width/6+width/80,height/10,width/3+width/15,height/20);
            font.getData().setScale(width/500);
            font.draw(batch,String.valueOf(actualIndexFundo*2+1)+"x",width/2+width/80,height/9);
            if(indexFundo<3){
                batch.draw(botaoComprar,width/2+width/10,height/8+height/5, width/4, height/15);
                batch.draw(coin,width/2+width/11,height/8+height/7+height/64,width/20,height/40);
                font.getData().setScale(width/300);
                font.draw(batch,String.valueOf(comprarFundo[indexFundo]),width/2+width/7,height/8+height/6+height/64);
            }
            if(actualIndexAnimal>0)
                batch.draw(setaEsquerda,width/15,height/2+height/7,width/13, height/22);
            if(actualIndexAnimal<indexAnimal)
                batch.draw(setaDireita,width/3+width/9,height/2+height/7,width/13, height/22);
            if(actualIndexFundo>0)
                batch.draw(setaEsquerda,width/15,height/4-height/25,width/13, height/22);
            if(actualIndexFundo<indexFundo)
                batch.draw(setaDireita,width/3+width/9,height/4-height/25,width/13, height/22);
        }
        font.getData().setScale(4);
        batch.end();
        passaroCirulo.set(height/11+(width/10)/2, posicaoy+(height/27)/2, (height/28)/2+1);
        canoBaixoRetangulo = new Rectangle(posicaoCanoX,height / 2 - 2*height/3 - espacoCanos / 2 + posicaoCanoY,width/7,2*height/3);
        canoTopoRetangulo = new Rectangle(posicaoCanoX,height / 2 + espacoCanos / 2 + posicaoCanoY,width/7,2*height/3);
        groundRetangulo = new Rectangle(0,0,width,height/8);
        if(Intersector.overlaps(passaroCirulo,canoBaixoRetangulo) || Intersector.overlaps(passaroCirulo,canoTopoRetangulo) || Intersector.overlaps(passaroCirulo,groundRetangulo)){
            if(estadoJogo==1) estadoJogo = 3;
        }
	}

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}