% 缩放变换，返回x与y的坐标集合
function [x_res, y_res] = scale(xc, yc, x, y, sx, sy)
[x_t, y_t] = translation(0, 0, x, y);
cor = [x_t;y_t];
cor(3,:) = 1;
res = [sx 0 0; 0 sy 0; 0 0 1] * cor;
[x_res, y_res] = translation(xc, yc, res(1,:), res(2,:));
x_res = round(x_res);
y_res = round(y_res);
end

