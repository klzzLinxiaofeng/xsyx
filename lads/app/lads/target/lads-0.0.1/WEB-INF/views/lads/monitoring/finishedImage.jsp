<%@ page contentType="image/JPEG" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" pageEncoding="UTF-8"%>
<%
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            int percent =0; // 进度百分比
            String spercent = request.getParameter("percent");
            if (spercent != null && !"".equals(spercent)) {
                percent =Integer.parseInt(spercent);
            }
            int strLeft = percent; // 字符移动位置
            String str = String.valueOf(percent) + "%"; // 进度百分比字符
            int width = 136, height = 36; // 图片长与宽

            int diverge;
            if (percent < 10) {
                diverge = 9;
            } else if (percent >= 10 && percent < 100) {
                diverge = 6;
            } else {
                diverge = 3;
            }

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();

            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, width, height);

            g.setColor(new Color(83, 152, 43));
            int X[] = {strLeft, strLeft + 34, strLeft + 34, strLeft + 22, strLeft + 17, strLeft + 12, strLeft}; // 顶点X坐标
            int Y[] = {1, 1, 17, 17, 22, 17, 17}; // 顶点Y坐标
            g.drawPolygon(X, Y, 7);

            g.setColor(new Color(98, 183, 38));
            int XX[] = {strLeft + 1, strLeft + 34, strLeft + 34, strLeft + 22, strLeft + 17, strLeft + 12, strLeft + 1};
            int YY[] = {2, 2, 17, 17, 22, 17, 17};
            g.fillPolygon(XX, YY, 7);

            g.setFont(new Font("Times New Roman", Font.PLAIN, 13));
            g.setColor(new Color(255, 255, 255));
            g.drawString(str, strLeft + diverge, 14);

            g.setColor(new Color(200, 200, 200));
            g.drawRoundRect(15, height - 13, 104, 11, 8, 8);

            g.setColor(new Color(88, 172, 12));
            g.drawRoundRect(17, height - 11, percent, 7, 8, 8);
            g.fillRoundRect(18, height - 10, percent, 6, 3, 3);

            g.dispose();

            ImageIO.write(image, "JPEG", response.getOutputStream());

            // 在使用完输出流以后调用,避免异常发生
            out.clear();
            out = pageContext.pushBody();
%>