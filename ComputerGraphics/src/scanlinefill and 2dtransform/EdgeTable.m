classdef EdgeTable < handle
    % 边表类，用于管理所有边
    
    properties
        size % 边表的大小
        edges = {} % 一个元胞数组，存储所有边
    end
    
    methods
        function obj = EdgeTable(size)
            % 构造此类的实例
            obj.size = size;
            obj.edges(size + 1) = {0};
        end
        
        function addEdge(obj, index, edge)
            % 向边表中添加边
            e = obj.edges{index}; % 获得元胞数组索引处的边
            if ~isempty(e) % 如果边不空，那么一直找它的下一个
                while ~isempty(e.getNext())
                    e = e.getNext();
                end
                e.setNext(edge); % 对该节点的next赋值
            else
                obj.edges(index) = {edge}; % 如果是空的，则直接插入
            end
        end
        
        function [edges] = getEdges(obj)
            % 获得边表
            edges  = obj.edges;
        end
        
        function [edge] = getEdge(obj, row, column)
            % 获得某一条边
            edge = obj.edges{row, column};
        end
    end
end

