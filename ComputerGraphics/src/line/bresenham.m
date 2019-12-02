% 使用Bresenham方法画线，返回x与y的坐标集合
function [x_res, y_res] = bresenham(x0, y0, x1, y1)
% 计算x,y的范围
x_range = x1 - x0;
y_range = y1 - y0;
% 计算斜率
m = y_range / x_range;
% 根据斜率大小执行不同操作
if abs(m) < 1
    slope = 1;
else
    slope = 0;
    [x0, y0] = swap(x0, y0);
    [x_range, y_range] = swap(x_range, y_range);
end
if m < 0
    sign = -1;
else
    sign = 1;
end
% 计算常量以及初始决策变量
c1 = 2 * y_range;
c2 = c1 - 2 * x_range;
p = c1 - x_range;

y = y0;

% 赋初值
x_res = [x0];
y_res = [y0];
% 循环计算点坐标
for x = x0:(x1-1)
    if p < 0
        if slope == 1
            x_res = [x_res, x+1];
            y_res = [y_res, y];
        else
            x_res = [x_res, y];
            y_res = [y_res, x+1];
        end
        p = p + c1;
    else
        y = y + sign;
        if slope == 1
            x_res = [x_res, x+1];
            y_res = [y_res, y];
        else
            x_res = [x_res, y];
            y_res = [y_res, x+1];
        end
        p = p + c2;
    end
end
end

function [a,b] = swap(a, b)
a = a + b;
b = a - b;
a = a - b;
end

