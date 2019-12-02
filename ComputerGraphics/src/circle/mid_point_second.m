% 二阶差分画圆法，返回x与y的坐标集合
function [x_res, y_res] = mid_point_second(x0, y0, r)
    % 设置起始点
    x = 0;
    y = r;
    % 计算初始决策变量
    p = 1 - r;
    x_res = [];
    y_res = [];
    while y > x
        if p < 0
            x = x + 1;
            p = p + (2 * x + 1);
        else
            x = x + 1;
            y = y - 1;
            p = p + (2 * x - 2 * y + 1);
        end
        % 求对称点
        x_res = [x_res, x + x0];
        y_res = [y_res, y + y0];
        
        x_res = [x_res, y + x0];
        y_res = [y_res, x + y0];
        
        x_res = [x_res, y + x0];
        y_res = [y_res, -x + y0];
        
        x_res = [x_res, x + x0];
        y_res = [y_res, -y + y0];
        
        x_res = [x_res, -x + x0];
        y_res = [y_res, -y + y0];
        
        x_res = [x_res, -y + x0];
        y_res = [y_res, -x + y0];
        
        x_res = [x_res, -y + x0];
        y_res = [y_res, x + y0];
        
        x_res = [x_res, -x + x0];
        y_res = [y_res, y + y0];
    end
    x_res = [x_res, x0 + x0];
    y_res = [y_res, r + y0];
    
    x_res = [x_res, x0 + x0];
    y_res = [y_res, -r + y0];
    
    x_res = [x_res, r + x0];
    y_res = [y_res, y0 + y0];
    
    x_res = [x_res, -r + x0];
    y_res = [y_res, y0 + y0];
   
end

