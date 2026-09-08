import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 面向本示例的轻量级 Swing 动画引擎。
 */
public final class MiniEngine {
    private static final int TICK_MILLIS = 16;
    private static final int ACTION_MILLIS = 650;

    private final SceneTimeline timeline;
    private final ScenePanel scenePanel;
    private final JLabel statusLabel;
    private final Timer timer;
    private JButton playButton;
    private int frameIndex;
    private double progress;
    private boolean playing;

    public MiniEngine(SceneTimeline timeline) {
        if (timeline == null) {
            throw new IllegalArgumentException("场景时间线不能为空。");
        }
        this.timeline = timeline;
        this.scenePanel = new ScenePanel();
        this.statusLabel = new JLabel();
        this.timer = new Timer(TICK_MILLIS, this::tick);
        updateStatus();
    }

    public void start() {
        JFrame window = new JFrame("七个葫芦娃排序 · Mini Engine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(scenePanel, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        playButton = new JButton("播放");
        JButton stepButton = new JButton("单步");
        JButton resetButton = new JButton("重置");
        playButton.addActionListener(event -> togglePlaying());
        stepButton.addActionListener(event -> step());
        resetButton.addActionListener(event -> reset());
        controls.add(playButton);
        controls.add(stepButton);
        controls.add(resetButton);
        controls.add(statusLabel);
        window.add(controls, BorderLayout.SOUTH);

        window.setSize(new Dimension(980, 540));
        window.setLocationByPlatform(true);
        window.setVisible(true);
    }

    private void togglePlaying() {
        playing = !playing;
        if (playing) {
            timer.start();
            playButton.setText("暂停");
        } else {
            timer.stop();
            playButton.setText("播放");
        }
    }

    private void step() {
        if (frameIndex >= timeline.size() - 1) {
            return;
        }
        frameIndex++;
        progress = 0;
        updateStatus();
        scenePanel.repaint();
    }

    private void reset() {
        playing = false;
        timer.stop();
        frameIndex = 0;
        progress = 0;
        playButton.setText("播放");
        updateStatus();
        scenePanel.repaint();
    }

    private void tick(ActionEvent event) {
        if (frameIndex >= timeline.size() - 1) {
            playing = false;
            timer.stop();
            playButton.setText("播放");
            updateStatus();
            return;
        }

        progress += (double) TICK_MILLIS / ACTION_MILLIS;
        if (progress >= 1) {
            progress -= 1;
            frameIndex++;
        }
        updateStatus();
        scenePanel.repaint();
    }

    private void updateStatus() {
        SceneFrame frame = timeline.frameAt(frameIndex);
        statusLabel.setText("  " + frame.getStep() + "/" + frame.getTotalSteps()
                + "  " + frame.getActionDescription());
    }

    private final class ScenePanel extends JPanel {
        private static final int LEFT_MARGIN = 70;
        private static final int RIGHT_MARGIN = 70;
        private static final int CARD_WIDTH = 92;
        private static final int CARD_HEIGHT = 72;
        private static final int TRACK_Y = 250;

        ScenePanel() {
            setPreferredSize(new Dimension(980, 450));
            setBackground(new Color(245, 247, 250));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawBackground(g);
            drawHeader(g);
            drawTrack(g);
            drawMembers(g);
            g.dispose();
        }

        private void drawBackground(Graphics2D g) {
            g.setPaint(new GradientPaint(0, 0, new Color(255, 255, 255), 0, getHeight(),
                    new Color(235, 240, 246)));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        private void drawHeader(Graphics2D g) {
            g.setColor(new Color(34, 43, 54));
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            g.drawString("七个葫芦娃排序", LEFT_MARGIN, 54);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            g.setColor(new Color(91, 103, 116));
            g.drawString("SortPlan -> Timeline -> Mini Engine", LEFT_MARGIN, 80);
        }

        private void drawTrack(Graphics2D g) {
            int slotWidth = slotWidth();
            g.setColor(new Color(190, 199, 209));
            g.setStroke(new BasicStroke(3));
            g.drawLine(LEFT_MARGIN + CARD_WIDTH / 2, TRACK_Y + CARD_HEIGHT / 2,
                    getWidth() - RIGHT_MARGIN - CARD_WIDTH / 2, TRACK_Y + CARD_HEIGHT / 2);
            g.setStroke(new BasicStroke(1));
            int memberCount = timeline.frameAt(0).getMembers().length;
            for (int i = 0; i < memberCount; i++) {
                int x = slotX(i + 1, slotWidth);
                g.setColor(new Color(216, 222, 229));
                g.drawRoundRect(x, TRACK_Y, CARD_WIDTH, CARD_HEIGHT, 14, 14);
                g.setColor(new Color(127, 140, 153));
                g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
                g.drawString("位置 " + (i + 1), x + 27, TRACK_Y + CARD_HEIGHT + 22);
            }
        }

        private void drawMembers(Graphics2D g) {
            SceneFrame current = timeline.frameAt(frameIndex);
            SceneFrame next = frameIndex + 1 < timeline.size()
                    ? timeline.frameAt(frameIndex + 1) : current;
            HuluwaSnapshot[] currentMembers = current.getMembers();
            HuluwaSnapshot[] nextMembers = next.getMembers();
            int slotWidth = slotWidth();

            for (int rank = 1; rank <= currentMembers.length; rank++) {
                HuluwaSnapshot member = findByRank(currentMembers, rank);
                HuluwaSnapshot destination = findByRank(nextMembers, rank);
                double movement = frameIndex + 1 < timeline.size() ? progress : 0;
                double x = interpolate(slotX(member.getPosition(), slotWidth),
                        slotX(destination.getPosition(), slotWidth), movement);
                double y = TRACK_Y;
                if (member.getPosition() != destination.getPosition()) {
                    double arc = Math.sin(movement * Math.PI) * 58;
                    y += rank % 2 == 0 ? arc : -arc;
                }
                drawMember(g, member, x, y);
            }
        }

        private void drawMember(Graphics2D g, HuluwaSnapshot member, double x, double y) {
            Color color = colorOf(member.getColor());
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 45));
            g.fill(new RoundRectangle2D.Double(x + 3, y + 5, CARD_WIDTH, CARD_HEIGHT, 14, 14));
            g.setColor(color);
            g.fill(new RoundRectangle2D.Double(x, y, CARD_WIDTH, CARD_HEIGHT, 14, 14));
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            g.drawString(String.valueOf(member.getRank()), (int) x + 12, (int) y + 27);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            g.drawString(member.getName(), (int) x + 31, (int) y + 27);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
            g.drawString(member.getColor(), (int) x + 31, (int) y + 48);
        }

        private int slotWidth() {
            int memberCount = timeline.frameAt(0).getMembers().length;
            return (getWidth() - LEFT_MARGIN - RIGHT_MARGIN - CARD_WIDTH)
                    / Math.max(1, memberCount - 1);
        }

        private int slotX(int position, int slotWidth) {
            return LEFT_MARGIN + (position - 1) * slotWidth;
        }

        private HuluwaSnapshot findByRank(HuluwaSnapshot[] members, int rank) {
            for (HuluwaSnapshot member : members) {
                if (member.getRank() == rank) {
                    return member;
                }
            }
            throw new IllegalStateException("场景中找不到排行为 " + rank + " 的葫芦娃。");
        }

        private double interpolate(double start, double end, double amount) {
            return start + (end - start) * amount;
        }

        private Color colorOf(String color) {
            return switch (color) {
                case "红色" -> new Color(218, 67, 70);
                case "橙色" -> new Color(232, 133, 48);
                case "黄色" -> new Color(218, 176, 39);
                case "绿色" -> new Color(75, 155, 91);
                case "青色" -> new Color(38, 155, 160);
                case "蓝色" -> new Color(65, 111, 192);
                case "紫色" -> new Color(132, 91, 177);
                default -> new Color(96, 108, 120);
            };
        }
    }
}
