package cn.com.mservlets.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthImg extends HttpServlet {

	// ����ͼ����֤�����ַ���������ʹ�С
	private Font myFont = new Font("Arial Black", Font.PLAIN, 16);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	// ���������ɫ
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ֹ���ɵ�ҳ�����ݱ����棬��֤ÿ���������������֤��
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// ָ��ͼ����֤��ͼƬ�Ĵ�С
		int width = 80, height = 20;
		// ����һ����ͼƬ
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// ��ͼƬ�л�������
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(myFont);
		// ���������������ͼƬ��������������
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);// ����x����
			int y = random.nextInt(height - 1);// ����y����
			int x1 = random.nextInt(6) + 1;// x��ƫ����
			int y1 = random.nextInt(12) + 1;// y��ƫ����
			g.drawLine(x, y, x + x1, y + y1);
		}
		// ���������������ͼƬ��������������
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(12) + 1;
			int y1 = random.nextInt(6) + 1;
			g.drawLine(x, y, x - x1, y - y1);
		}

		// �ñ�����������ϵͳ���ɵ�����ַ���
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			// ȡ��һ������ַ�
			String tmp = getRandomChar();
			sRand += tmp;
			// ��ϵͳ���ɵ�����ַ���ӵ�ͼ����֤��ͼƬ��
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(tmp, 15 * i + 10, 15);
		}

		// ȡ���û�Session
		HttpSession session = request.getSession(true);
		// ��ϵͳ���ɵ�ͼ����֤�����
		session.setAttribute("rand", sRand);
		g.dispose();
		// ���ͼ����֤��ͼƬ
		ImageIO.write(image, "JPEG", response.getOutputStream());

	}

	// �������һ���ַ�
	private String getRandomChar() {
        int rand = (int) Math.round(Math.random() * 2);// ��0��2��С������������������
        long itmp = 0;
        String temp = "\u00000";
        char ctmp = temp.charAt(0);
        // ����rand��ֵ������������һ����д��ĸ��Сд��ĸ������
        switch (rand) {
        // ���ɴ�д��ĸ������
        case 1:
            itmp = Math.round(Math.random() * 25 + 65);
            ctmp = (char) itmp;
            return String.valueOf(ctmp);
            // ����Сд��ĸ
        case 2:
            itmp = Math.round(Math.random() * 25 + 97);
            ctmp = (char) itmp;
            return String.valueOf(ctmp);
            // ��������
        default:
            itmp = Math.round(Math.random() * 9);
            return String.valueOf(itmp);
        }
    }
}
