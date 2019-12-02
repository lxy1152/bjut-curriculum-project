% 平移变换，返回x与y的结果集合
function [x_res, y_res] = translation(xc, yc, x, y)
tx = -(x(1) - xc);
ty = -(y(1) - yc);
trans = [1 0 0; 0 1 0; tx ty 1];
cor = [x' y'];
cor(:,3) = 1;
res = cor*trans;
x_res = res(:,1)';
y_res = res(:,2)';
end

