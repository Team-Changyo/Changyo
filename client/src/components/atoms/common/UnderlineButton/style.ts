import { styled, css } from 'styled-components';

// 버튼 타입에 따른 스타일 매핑 객체
const buttonStyles = {
	Normal: css`
		color: var(--gray-400);
		text-decoration: underline 1px solid var(--gray-400);
	`,
	Primary: css`
		color: var(--main-color);
		text-decoration: underline 1px solid var(--main-color);
	`,
	Danger: css`
		color: var(--danger-color);
		text-decoration: underline 1px solid var(--danger-color);
	`,
};

export const UnderlineButtonWrapper = styled.button<{ $type: 'Normal' | 'Primary' | 'Danger' }>`
	${({ $type }) => buttonStyles[$type]}
`;
