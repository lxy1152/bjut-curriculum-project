classdef Edge < handle
    % 边类，用于储存边的信息
    
    properties
        x_low
        dx
        y_max
        next
    end
    
    methods
        function obj = Edge(x_low, dx, y_max)
            %EDGE 构造此类的实例
            if nargin > 2
                obj.x_low = x_low;
                obj.dx = dx;
                obj.y_max = y_max;
            end
        end
        
        function setNext(obj, next)
            % 设置下一条边
            obj.next = next;
        end
        
        function [next] = getNext(obj)
            % 获得下一条边
            next = obj.next;
        end
        
        function [x_low] = getXLow(obj)
            % 获得最低点对应的x值
            x_low = obj.x_low;
        end

        function [y_max] = getYMax(obj)
            % 获得y最大值
            y_max = obj.y_max;
        end

        function [dx] = getDx(obj)
            % 获得x增长率
            dx = obj.dx;
        end
    end
end

