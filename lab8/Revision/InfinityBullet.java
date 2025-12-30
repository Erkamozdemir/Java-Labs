public class InfinityBullet extends Bullet_Rev{
    public InfinityBullet(int x, int y) {
        super(x, y);
        this.symbol = '*';
    }
    @Override
    public boolean shouldDisappearAfterCollision(){
        return false;
    }
}